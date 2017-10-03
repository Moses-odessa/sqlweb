package ua.moses.sqlweb.model;

import java.sql.Connection;
import java.util.List;

public interface DataBaseManager {
    Connection connect (String database, String userName, String password) throws RuntimeException;
    List<String> getTables(Connection connection) throws RuntimeException;
    List<String> getTableColumns(Connection connection, String tableName) throws RuntimeException;
    void createTable(Connection connection, String tableName) throws RuntimeException;
    void addColumn(Connection connection, String tableName, String columnName) throws RuntimeException;
    void delColumn(Connection connection, String tableName, String columnName);
    void dropTable(Connection connection, String tableName) throws RuntimeException;
    void clearTable(Connection connection, String tableName) throws RuntimeException;
    List<List<Object>> getTableData(Connection connection, String tableName, String sortColumn) throws RuntimeException;
    void insertRecord (Connection connection, String tableName, String[] columns, String[] values) throws RuntimeException;
    void delRecord(Connection connection, String tableName, String[] criteriaColumns, String[] criteriaValues);
    void updateRecord (Connection connection, String tableName, String[] criteriaColumns, String[] criteriaValues, String[] setColumns, String[] setValues);
}
