package ua.moses.sqlweb.service.dbcommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.moses.sqlweb.model.DataBaseManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;

@Component
public class UpdateRecord extends DbCommand {
    @Autowired
    private DataBaseManager dataBaseManager;

    UpdateRecord() {
        super(CommandsHref.UPDATE_RECORD);
    }

    @Override
    public void run(HttpServletRequest req, Connection connection) {
        String tableName = req.getParameter("table_name");
        String[] columns = req.getParameterValues("columns[]");
        String[] oldValues = req.getParameterValues("old_values[]");
        String[] newValues = req.getParameterValues("new_values[]");
        dataBaseManager.updateRecord(connection, tableName, columns, oldValues, columns, newValues);
    }
}
