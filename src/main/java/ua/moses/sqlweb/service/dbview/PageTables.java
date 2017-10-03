package ua.moses.sqlweb.service.dbview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.moses.sqlweb.model.DataBaseManager;
import ua.moses.sqlweb.service.dbcommand.CommandsHref;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.HashMap;

@Component
public class PageTables extends DbView {
    @Autowired
    private DataBaseManager dataBaseManager;

    public PageTables() {
        super(ViewHref.TABLES);
    }

    @Override
    public void set(HttpServletRequest req) throws Exception {
        super.set(req);
        Connection connection = (Connection) req.getSession().getAttribute("db_connection");
        HashMap parameters = new HashMap();

        parameters.put("view_table_data", ViewHref.TABLE_DATA);
        parameters.put("view_tables", ViewHref.TABLES);
        parameters.put("command_delete", CommandsHref.DROP_TABLE);
        parameters.put("command_clear", CommandsHref.CLEAR_TABLE);
        parameters.put("command_create", CommandsHref.CREATE_TABLE);
        parameters.put("tables", dataBaseManager.getTables(connection));
        setVariables(req, parameters);

    }
}
