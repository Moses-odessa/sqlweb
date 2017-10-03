package ua.moses.sqlweb.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.moses.sqlweb.model.DataBaseManager;
import ua.moses.sqlweb.service.dbview.DbView;
import ua.moses.sqlweb.service.dbview.DbViewFactory;
import ua.moses.sqlweb.service.dbview.ViewHref;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;

@Component
public class ServiceImpl implements Service {

    @Autowired
    private DataBaseManager dataBaseManager;

    @Autowired
    private DbViewFactory dbViewFactory;

    @Override
    public Connection connect(String databaseName, String userName, String password) {
        return dataBaseManager.connect(databaseName, userName, password);
    }

    @Override
    public void setMenuData(HttpServletRequest req) {
        req.setAttribute("menu", ViewHref.getMenu());
    }

    @Override
    public void setContentData(HttpServletRequest req) {
        try {
            DbView currentDbView = dbViewFactory.getDbView(getAction(req));
            currentDbView.set(req);
        } catch (Exception e) {
            req.setAttribute("current_page", ViewHref.ERROR);
            req.setAttribute("error_text", e.getMessage());
        }

    }

    private String getAction(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        return requestURI.substring(req.getContextPath().length() + 1, requestURI.length());
    }
}
