package ua.moses.sqlweb.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class PostgresManager implements DataBaseManager {

    public Connection connect(String database, String userName, String password) throws RuntimeException {
        Properties properties = new Properties();
        String SERVER_NAME;
        String SERVER_PORT;
        try (FileInputStream propertiesFile =
                     new FileInputStream(new File("src/main/resources/postgres.properties"))) {
            properties.load(propertiesFile);
            SERVER_NAME = properties.getProperty("SERVER_NAME");
            SERVER_PORT = properties.getProperty("SERVER_PORT");
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


    public Set<String> getTables(Connection connection) throws RuntimeException {
        String sql = "SELECT tablename FROM pg_catalog.pg_tables where schemaname = 'public'";
        Set<String> result = new LinkedHashSet<>();
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

    public boolean createTable(Connection connection, String tableName, String[] columnsName) throws RuntimeException {
        StringBuilder columnsQuery = new StringBuilder();
        for (int i = 0; i < columnsName.length; i++) {
            columnsQuery.append(columnsName[i]).append(" text");
            if (i < columnsName.length - 1) {
                columnsQuery.append(",\n");
            }
        }
        String sql = String.format("CREATE TABLE public.%s (%s) TABLESPACE pg_default", tableName, columnsQuery);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return true;
    }


    public boolean dropTable(Connection connection, String tableName) throws RuntimeException {
        String sql = "DROP TABLE public." + tableName;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return true;
    }

    public int clearTable(Connection connection, String tableName) throws RuntimeException {
        String sql = "DELETE FROM public." + tableName;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Record> getTableData(Connection connection, String tableName, String sortColumn) throws RuntimeException {
        String sql = "SELECT * FROM public." + tableName;
        if (sortColumn.length() > 0){
            sql+= " ORDER BY " + sortColumn;
        }
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnsCount = metaData.getColumnCount();
            Record columns = new MyRecord(columnsCount);
            for (int i = 0; i < columnsCount; i++) {
                columns.set(i, metaData.getColumnName(i + 1));
            }
            List<Record> result = new LinkedList<>();
            result.add(columns);
            while (resultSet.next()) {
                Record currentRow = new MyRecord(columnsCount);
                for (int i = 0; i < columnsCount; i++) {
                    currentRow.set(i, resultSet.getString(i + 1));
                }
                result.add(currentRow);
            }
            return result;

        } catch (SQLException e) {

            throw new RuntimeException(e.getMessage());
        }
    }

    public int insertRecord(Connection connection, String tableName, String[] columns, String[] values) throws RuntimeException {
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
        String sql = "INSERT INTO public." + tableName + "\n" +
                "(" + columnsQuery + ")\n" +
                "VALUES (" + valuesQuery + ")\n";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public int updateRecord(Connection connection, String tableName, String criteriaColumn, String criteriaValue,
                            String setColumn, String setValue) {
        String sql = String.format("UPDATE public.%s SET %s = '%s' WHERE %s = '%s'",
                tableName, setColumn, setValue, criteriaColumn, criteriaValue);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public int deleteRecord(Connection connection, String tableName, String criteriaColumn, String criteriaValue) {
        String sql = String.format("DELETE FROM public.%s WHERE %s = '%s'", tableName, criteriaColumn, criteriaValue);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
