package ua.moses.sqlweb.service;

import ua.moses.sqlweb.model.DataBaseManager;
import ua.moses.sqlweb.model.PostgresManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;

public class ServiceImpl implements Service {

    @Override
    public Connection connect(String databaseName, String userName, String password) {
        return new PostgresManager().connect(databaseName, userName, password);
    }


    @Override
    public void setAttributes(HttpServletRequest req, MenuItem currentMenuItem) {
        req.setAttribute("menu", MainMenu.getMenu());
        req.setAttribute("current_page", currentMenuItem);
        Connection connection = (Connection) req.getSession().getAttribute("db_connection");
        req.setAttribute("connected", connection != null);

        try {
            DataBaseManager db = new PostgresManager();
            switch (currentMenuItem.getMenuEnum()) {
                case GET_TABLES:
                    req.setAttribute("tables", db.getTables(connection));
                    break;
                case CONNECT:
                    if (connection != null) {
                        req.setAttribute("connected_user", connection.getMetaData().getUserName());
                        req.setAttribute("connected_base", connection.getCatalog());
                    }

                    break;

            }
        } catch (SQLException | RuntimeException e) {
            req.setAttribute("current_page", MainMenu.ERROR.getMenuItem());
            req.setAttribute("menu", MainMenu.getMenu());
            req.setAttribute("error_text", e.getMessage());
        }
    }


    @Override
    public void doPost(HttpServletRequest req, MenuItem currentMenuItem) {
        try {
            switch (currentMenuItem.getMenuEnum()) {
                case CONNECT:
                    String databaseName = req.getParameter("dbname");
                    String userName = req.getParameter("username");
                    String password = req.getParameter("password");
                    req.getSession().setAttribute("db_connection", connect(databaseName, userName, password));
                    break;

            }
            setAttributes(req, currentMenuItem);
        } catch (RuntimeException e) {
            req.setAttribute("current_page", MainMenu.ERROR.getMenuItem());
            req.setAttribute("error_text", e.getMessage());
        }

    }


}
