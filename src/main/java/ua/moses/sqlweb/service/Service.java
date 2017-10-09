package ua.moses.sqlweb.service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;

public interface Service {
    void setMenuData(HttpServletRequest req);
    void setContentData(HttpServletRequest req);
}
