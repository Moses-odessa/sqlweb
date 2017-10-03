package ua.moses.sqlweb.model;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

@Component
public class PostgresManager implements DataBaseManager {

    private String SHEMA_NAME;

    public Connection connect(String database, String userName, String password) throws RuntimeException {
        Properties properties = new Properties();
        String SERVER_NAME;
        String SERVER_PORT;

        try (FileInputStream propertiesFile =
                     new FileInputStream(new File("src/main/resources/postgres.properties"))) {
            properties.load(propertiesFile);
            SERVER_NAME = properties.getProperty("SERVER_NAME");
            SERVER_PORT = properties.getProperty("SERVER_PORT");
            SHEMA_NAME = properties.getProperty("SHEMA_NAME");
        } catch (IOException e) {
            throw new RuntimeException("Невозможно прочитать файл настроек для подключенияк БД. " + e.getMessage());
        }
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Не подключен jdbc драйвер");
        }
        try {
            return DriverManager.getConnection("jdbc:postgresql://" + SERVER_NAME + ":" + SERVER_PORT + "/"
                    + database, userName, password);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    public List<String> getTables(Connection connection) throws RuntimeException {
        if (connection == null) throw new RuntimeException("Data Base not connected!!!! Please connect first!!!");

        String sql = "SELECT tablename FROM pg_catalog.pg_tables where schemaname = '" + SHEMA_NAME + "'";
        List<String> result = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                result.add(resultSet.getString(1));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public void createTable(Connection connection, String tableName) throws RuntimeException {
        String sql = String.format("CREATE TABLE " + SHEMA_NAME + ".%s () TABLESPACE pg_default", tableName);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void addColumn(Connection connection, String tableName, String columnName) throws RuntimeException {
        String sql = "ALTER TABLE " + SHEMA_NAME + "." + tableName + " ADD COLUMN " + columnName + " text";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delColumn(Connection connection, String tableName, String columnName) {
        String sql = "ALTER TABLE " + SHEMA_NAME + "." + tableName + " DROP COLUMN " + columnName;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void dropTable(Connection connection, String tableName) throws RuntimeException {
        String sql = "DROP TABLE " + SHEMA_NAME + "." + tableName;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void clearTable(Connection connection, String tableName) throws RuntimeException {
        String sql = "DELETE FROM " + SHEMA_NAME + "." + tableName;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<List<Object>> getTableData(Connection connection, String tableName, String sortColumn) throws RuntimeException {
        String sql = "SELECT * FROM " + SHEMA_NAME + "." + tableName;
        if (sortColumn != null && sortColumn.length()>0) {
            sql += " ORDER BY " + sortColumn;
        }
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnsCount = metaData.getColumnCount();
            List<List<Object>> result = new LinkedList<>();
            while (resultSet.next()) {
                List<Object> currentRow = new ArrayList<>(columnsCount);
                for (int i = 0; i < columnsCount; i++) {
                    currentRow.add(resultSet.getObject(i + 1)); //http://www.javaportal.ru/java/tutorial/tutorialJDBC/mapping.html приведение типов данных
                }
                result.add(currentRow);
            }
            return result;

        } catch (SQLException e) {

            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<String> getTableColumns(Connection connection, String tableName) throws RuntimeException {
        String sql = "SELECT * FROM " + SHEMA_NAME + "." + tableName;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnsCount = metaData.getColumnCount();
            List<String> columns = new ArrayList<>(columnsCount);
            for (int i = 0; i < columnsCount; i++) {
                columns.add(metaData.getColumnName(i + 1));
            }
            return columns;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void insertRecord(Connection connection, String tableName, String[] columns, String[] values) throws RuntimeException {
        StringBuilder columnsQuery = new StringBuilder();
        StringBuilder valuesQuery = new StringBuilder();
        for (int i = 0; i < columns.length; i++) {
            columnsQuery.append(columns[i]);
            if (i < columns.length - 1) {
                columnsQuery.append(", ");
            }
        }
        for (int i = 0; i < values.length; i++) {
            valuesQuery.append("'").append(values[i]).append("'");
            if (i < values.length - 1) {
                valuesQuery.append(", ");
            }
        }
        String sql = "INSERT INTO " + SHEMA_NAME + "." + tableName + "\n" +
                "(" + columnsQuery + ")\n" +
                "VALUES (" + valuesQuery + ")\n";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void updateRecord(Connection connection, String tableName, String[] criteriaColumns, String[] criteriaValues,
                             String[] setColumns, String[] setValues) {
        String sql = String.format("UPDATE " + SHEMA_NAME + ".%s SET %s WHERE %s",
                tableName, getSet(setColumns, setValues), getWhere(criteriaColumns, criteriaValues));
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delRecord(Connection connection, String tableName, String[] criteriaColumns, String[] criteriaValues) {
        String sql = String.format("DELETE FROM " + SHEMA_NAME + ".%s WHERE %s", tableName, getWhere(criteriaColumns, criteriaValues));

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private String getWhere(String[] criteriaColumns, String[] criteriaValues) {
        StringBuilder sql = new StringBuilder("(");
        for (int i = 0; i < criteriaColumns.length; i++) {
            if (!criteriaValues[i].isEmpty()) {
                sql.append(criteriaColumns[i]).append("='").append(criteriaValues[i]).append("'");
            } else {
                sql.append("(").append(criteriaColumns[i]).append("='").append(criteriaValues[i]).append("' OR ").append(criteriaColumns[i]).append(" IS NULL)");
            }
            if (i != criteriaColumns.length - 1) {
                sql.append(" AND ");
            } else {
                sql.append(")");
            }
        }
        return sql.toString();
    }

    private String getSet(String[] criteriaColumns, String[] criteriaValues) {
        StringBuilder sql = new StringBuilder("");
        for (int i = 0; i < criteriaColumns.length; i++) {
            sql.append(criteriaColumns[i]).append(" = '").append(criteriaValues[i]).append("'");
            if (i != criteriaColumns.length - 1) {
                sql.append(", ");
            }
        }
        return sql.toString();
    }
}
