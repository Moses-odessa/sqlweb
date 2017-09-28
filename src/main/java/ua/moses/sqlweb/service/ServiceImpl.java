package ua.moses.sqlweb.service;

import org.springframework.stereotype.Component;
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
    public void setMenuData(HttpServletRequest req) {
        MenuItem currentMenuItem = getMenuItem(req);
        req.setAttribute("menu", Command.getMenu());
    }

    private MenuItem getMenuItem(HttpServletRequest req) {
        String action = getAction(req);
        MenuItem currentMenuItem = Command.getMenuItemByLink(action);
        if (currentMenuItem == null) {
            currentMenuItem = Command.HELP.getMenuItem();
        }
        return currentMenuItem;
    }


    @Override
    public void setContentData(HttpServletRequest req) {
        MenuItem currentMenuItem = getMenuItem(req);
        Connection connection = (Connection) req.getSession().getAttribute("db_connection");
        req.setAttribute("connected", connection != null);
        req.setAttribute("current_page", currentMenuItem);

        try {
            DataBaseManager db = new PostgresManager();
            String tableName;
            String columnName;
            switch (currentMenuItem.getCommand()) {
                case GET_TABLES:
                    req.setAttribute("tables", db.getTables(connection));
                    req.setAttribute("command_view", Command.VIEW_TABLE_DATA.getMenuItem());
                    req.setAttribute("command_delete", Command.DROP_TABLE.getMenuItem());
                    req.setAttribute("command_clear", Command.CLEAR_TABLE.getMenuItem());
                    req.setAttribute("command_create", Command.CREATE_TABLE.getMenuItem());
                    break;
                case CONNECT:
                    if (connection != null) {
                        req.setAttribute("connected_user", connection.getMetaData().getUserName());
                        req.setAttribute("connected_base", connection.getCatalog());
                    }
                    break;
                case VIEW_TABLE_DATA:
                    tableName = req.getParameter("table_name");
                    columnName = req.getParameter("sort_column_name");
                    req.setAttribute("table_name", tableName);
                    req.setAttribute("table_columns", db.getTableColumns(connection, tableName));
                    req.setAttribute("table_data", db.getTableData(connection, tableName, columnName));
                    req.setAttribute("command_add_column", Command.ADD_COLUMN.getMenuItem());
                    req.setAttribute("command_del_column", Command.DEL_COLUMN.getMenuItem());
                    req.setAttribute("command_insert", Command.INSERT_RECORD.getMenuItem());
                    req.setAttribute("command_del_record", Command.DEL_RECORD.getMenuItem());
                    req.setAttribute("command_edit", Command.EDIT_RECORD.getMenuItem());
                    req.setAttribute("command_view", Command.VIEW_TABLE_DATA.getMenuItem());
                    break;
                case DROP_TABLE:
                    tableName = req.getParameter("table_name");
                    db.dropTable(connection, tableName);
                    //setContentData(req, Command.GET_TABLES.getMenuItem());
                    break;
                case CLEAR_TABLE:
                    tableName = req.getParameter("table_name");
                    db.clearTable(connection, tableName);
                    //setContentData(req, Command.GET_TABLES.getMenuItem());
                    break;
                case DEL_COLUMN:
                    tableName = req.getParameter("table_name");
                    columnName = req.getParameter("column_name");
                    db.delColumn(connection, tableName, columnName);
                    //setContentData(req, Command.VIEW_TABLE_DATA.getMenuItem());
                    break;
            }
        } catch (SQLException | RuntimeException e) {
            req.setAttribute("current_page", Command.ERROR.getMenuItem());
            req.setAttribute("error_text", e.getMessage());
        }
    }


    @Override
    public void doPost(HttpServletRequest req) {
        MenuItem currentMenuItem = getMenuItem(req);
        Connection connection = (Connection) req.getSession().getAttribute("db_connection");
        try {
            DataBaseManager db = new PostgresManager();
            String tableName;
            String columnName;
            String[] columns;
            String[] values;
            switch (currentMenuItem.getCommand()) {
                case CONNECT:
                    String databaseName = req.getParameter("dbname");
                    String userName = req.getParameter("username");
                    String password = req.getParameter("password");
                    req.getSession().setAttribute("db_connection", connect(databaseName, userName, password));
                    //setContentData(req, Command.GET_TABLES.getMenuItem());
                    break;
                case CREATE_TABLE:
                    tableName = req.getParameter("table_name");
                    db.createTable(connection, tableName);
                    //setContentData(req, Command.VIEW_TABLE_DATA.getMenuItem());
                    break;
                case ADD_COLUMN:
                    tableName = req.getParameter("table_name");
                    columnName = req.getParameter("column_name");
                    db.addColumn(connection, tableName, columnName);
                    //setContentData(req, Command.VIEW_TABLE_DATA.getMenuItem());
                    break;
                case INSERT_RECORD:
                    tableName = req.getParameter("table_name");
                    columns = req.getParameterValues("insert_columns[]");
                    values = req.getParameterValues("insert_values[]");
                    db.insertRecord(connection, tableName, columns, values);
                    //setContentData(req, Command.VIEW_TABLE_DATA.getMenuItem());
                    break;
                case DEL_RECORD:
                    tableName = req.getParameter("table_name");
                    columns = req.getParameterValues("columns[]");
                    values = req.getParameterValues("values[]");
                    db.deleteRecord(connection, tableName, columns, values);
                    //setContentData(req, Command.VIEW_TABLE_DATA.getMenuItem());
                    break;
                case EDIT_RECORD:
                    tableName = req.getParameter("table_name");
                    columns = req.getParameterValues("columns[]");
                    values = req.getParameterValues("values[]");
                    req.setAttribute("table_name", tableName);
                    req.setAttribute("table_columns", columns);
                    req.setAttribute("values", values);
                    req.setAttribute("command_update", Command.UPDATE_RECORD.getMenuItem());
                    //setContentData(req, currentMenuItem);
                    break;
                case UPDATE_RECORD:
                    tableName = req.getParameter("table_name");
                    columns = req.getParameterValues("columns[]");
                    String[] oldValues = req.getParameterValues("old_values[]");
                    String[] newValues = req.getParameterValues("new_values[]");
                    db.updateRecord(connection, tableName, columns, oldValues, columns, newValues);
                    req.setAttribute("table_name", tableName);
                    //setContentData(req, Command.VIEW_TABLE_DATA.getMenuItem());
                    break;

            }
        } catch (RuntimeException e) {
            req.setAttribute("menu", Command.getMenu());
            req.setAttribute("current_page", Command.ERROR.getMenuItem());
            req.setAttribute("error_text", e.getMessage());
        }

    }

    private String getAction(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        return requestURI.substring(req.getContextPath().length() + 1, requestURI.length());
    }

}
