package ua.moses.sqlweb.service.dbview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.moses.sqlweb.model.DataBaseManager;
import ua.moses.sqlweb.service.dbcommand.CommandsHref;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.HashMap;

@Component
public class PageTablesData extends DbView {
    @Autowired
    private DataBaseManager dataBaseManager;

    public PageTablesData() {
        super(ViewHref.TABLE_DATA);
    }

    @Override
    public void set(HttpServletRequest req) throws Exception {
        super.set(req);
        Connection connection = (Connection) req.getSession().getAttribute("db_connection");
        String tableName = req.getParameter("table_name");
        String columnName = req.getParameter("sort_column_name");
        HashMap parameters = new HashMap();
        parameters.put("table_name", tableName);
        parameters.put("table_columns", dataBaseManager.getTableColumns(connection, tableName));
        parameters.put("table_data", dataBaseManager.getTableData(connection, tableName, columnName));
        parameters.put("command_add_column", CommandsHref.ADD_COLUMN);
        parameters.put("command_del_column", CommandsHref.DEL_COLUMN);
        parameters.put("command_insert", CommandsHref.INSERT_RECORD);
        parameters.put("command_del_record", CommandsHref.DEL_RECORD);
        parameters.put("view_edit_record", ViewHref.EDIT_RECORD);
        setVariables(req, parameters);

    }
}
