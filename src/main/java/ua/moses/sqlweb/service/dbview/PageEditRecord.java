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
public class PageEditRecord extends DbView {
    @Autowired
    private DataBaseManager dataBaseManager;

    public PageEditRecord() {
        super(ViewHref.EDIT_RECORD);
    }

    @Override
    public void set(Model model, HttpSession session) throws Exception {
        super.set(model, session);
        Connection connection = (Connection) session.getAttribute("db_connection");
        Map<String, Object> parameters = new HashMap<>();
        model.mergeAttributes(parameters);
        String tableName = (String) parameters.get("table_name");
        String[] columns = (String[]) parameters.get("columns[]");
        String[] values = (String[]) parameters.get("values[]");
        parameters = new HashMap<>();
        parameters.put("table_name", tableName);
        parameters.put("table_columns", columns);
        parameters.put("values", values);
        parameters.put("command_update", CommandsHref.UPDATE_RECORD);
        parameters.put("view_table_data", ViewHref.TABLE_DATA);
        model.addAllAttributes(parameters);
    }
}
