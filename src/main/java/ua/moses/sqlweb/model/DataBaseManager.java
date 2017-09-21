package ua.moses.sqlweb.model;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

public interface DataBaseManager {
    Connection connect (String database, String userName, String password) throws RuntimeException;
    Set<String> getTables(Connection connection) throws RuntimeException;
    boolean createTable(Connection connection, String tableName, String[] columnsName) throws RuntimeException;
    boolean dropTable(Connection connection, String tableName) throws RuntimeException;
    int clearTable(Connection connection, String tableName) throws RuntimeException;
    List<Record> getTableData(Connection connection, String tableName, String sortColumn) throws RuntimeException;
    int insertRecord (Connection connection, String tableName, String[] columns, String[] values) throws RuntimeException;
    int updateRecord (Connection connection, String tableName, String criteriaColumn, String criteriaValue, String setColumn, String setValue);
    int deleteRecord (Connection connection, String tableName, String criteriaColumn, String criteriaValue);
}
