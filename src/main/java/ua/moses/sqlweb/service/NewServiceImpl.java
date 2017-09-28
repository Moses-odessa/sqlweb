package ua.moses.sqlweb.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.moses.sqlweb.model.DataBaseManager;
import ua.moses.sqlweb.service.dbview.DbViewFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;

@Component
public class NewServiceImpl implements Service {

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
        req.setAttribute("menu", dbViewFactory.getMenu(getAction(req)));
    }

    @Override
    public void setContentData(HttpServletRequest req) {

    }

    @Override
    public void doPost(HttpServletRequest req) {

    }

    private String getAction(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        return requestURI.substring(req.getContextPath().length() + 1, requestURI.length());
    }
}
