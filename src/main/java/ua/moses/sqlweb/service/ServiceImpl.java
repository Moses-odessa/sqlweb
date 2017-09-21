package ua.moses.sqlweb.service;

import ua.moses.sqlweb.model.DataBaseManager;
import ua.moses.sqlweb.model.PostgresManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;

public class ServiceImpl implements Service {

    @Override
    public Connection connect(String databaseName, String userName, String password) {
        return new PostgresManager().connect(databaseName, userName, password);
    }


    @Override
    public void setAttributes(Connection connection, HttpServletRequest req, MenuItem currentMenuItem) {
        req.setAttribute("menu", MainMenu.getMenu());
        req.setAttribute("current_page", currentMenuItem);
        try {
            DataBaseManager db = new PostgresManager();
            switch (currentMenuItem.getMenuEnum()) {
                case GET_TABLES:
                    req.setAttribute("tables", db.getTables(connection));
                    break;

            }
        } catch (RuntimeException e) {
            req.setAttribute("current_page", MainMenu.ERROR.getMenuItem());
            req.setAttribute("menu", MainMenu.getMenu());
            req.setAttribute("error_text", e.getMessage());
        }
    }

    @Override
    public void doPost(Connection connection, HttpServletRequest req, MenuItem currentMenuItem) {
        req.setAttribute("menu", MainMenu.getMenu());
        req.setAttribute("current_page", currentMenuItem);
        try {
            switch (currentMenuItem.getMenuEnum()) {
                case CONNECT:
                    String databaseName = req.getParameter("dbname");
                    String userName = req.getParameter("username");
                    String password = req.getParameter("password");
                    req.getSession().setAttribute("db_connection", connect(databaseName, userName, password));
                    req.setAttribute("current_page", MainMenu.CONNECT.getMenuItem());
                    break;

            }
        } catch (RuntimeException e) {
            req.setAttribute("current_page", MainMenu.ERROR.getMenuItem());
            req.setAttribute("error_text", e.getMessage());
        }

    }


}
