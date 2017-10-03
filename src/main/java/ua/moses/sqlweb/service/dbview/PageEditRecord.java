package ua.moses.sqlweb.service.dbview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.moses.sqlweb.model.DataBaseManager;
import ua.moses.sqlweb.service.dbcommand.CommandsHref;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.HashMap;

@Component
public class PageEditRecord extends DbView {
    @Autowired
    private DataBaseManager dataBaseManager;

    public PageEditRecord() {
        super(ViewHref.EDIT_RECORD);
    }

    @Override
    public void set(HttpServletRequest req) throws Exception {
        super.set(req);
        Connection connection = (Connection) req.getSession().getAttribute("db_connection");
        String tableName = req.getParameter("table_name");
        String[] columns = req.getParameterValues("columns[]");
        String[] values = req.getParameterValues("values[]");
        HashMap parameters = new HashMap();
        parameters.put("table_name", tableName);
        parameters.put("table_columns", columns);
        parameters.put("values", values);
        parameters.put("command_update", CommandsHref.UPDATE_RECORD);
        parameters.put("view_table_data", ViewHref.TABLE_DATA);

        setVariables(req, parameters);

    }
}
