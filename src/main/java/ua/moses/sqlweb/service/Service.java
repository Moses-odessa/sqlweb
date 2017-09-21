package ua.moses.sqlweb.service;

import ua.moses.sqlweb.model.DataBaseManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface Service {
    DataBaseManager connect(String databaseName, String userName, String password);
    Map<String, Object> getAttributes(MenuItem menuItem);

    void setAttributes(HttpServletRequest req, MenuItem currentMenuItem);
}
