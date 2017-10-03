package ua.moses.sqlweb.service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;

public interface Service {
    Connection connect(String databaseName, String userName, String password);
    void setMenuData(HttpServletRequest req);
    void setContentData(HttpServletRequest req);
}
