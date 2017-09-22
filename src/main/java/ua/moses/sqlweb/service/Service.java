package ua.moses.sqlweb.service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;

public interface Service {
    Connection connect(String databaseName, String userName, String password);

    void doGet(HttpServletRequest req, MenuItem currentMenuItem);

    MenuItem doPost(HttpServletRequest req, MenuItem currentMenuItem);
}
