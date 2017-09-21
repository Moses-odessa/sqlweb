package ua.moses.sqlweb.service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.Map;

public interface Service {
    Connection connect(String databaseName, String userName, String password);

    void setAttributes(Connection connection, HttpServletRequest req, MenuItem currentMenuItem);

    void doPost(Connection connection, HttpServletRequest req, MenuItem currentMenuItem);
}
