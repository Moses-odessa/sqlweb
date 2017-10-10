package ua.moses.sqlweb.service.dbview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import ua.moses.sqlweb.model.DataBaseManager;
import ua.moses.sqlweb.service.dbcommand.CommandsHref;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

@Component
public class PageTables extends DbView {
    @Autowired
    private DataBaseManager dataBaseManager;

    public PageTables() {
        super(ViewHref.TABLES);
    }

    @Override
    public void set(Model model, HttpSession session) throws Exception {
        super.set(model, session);
        Connection connection = (Connection) session.getAttribute("db_connection");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("view_table_data", ViewHref.TABLE_DATA);
        parameters.put("view_tables", ViewHref.TABLES);
        parameters.put("command_delete", CommandsHref.DROP_TABLE);
        parameters.put("command_clear", CommandsHref.CLEAR_TABLE);
        parameters.put("command_create", CommandsHref.CREATE_TABLE);
        parameters.put("tables", dataBaseManager.getTables(connection));
        model.addAllAttributes(parameters);
    }
}
