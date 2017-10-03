package ua.moses.sqlweb.service.dbcommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.moses.sqlweb.model.DataBaseManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;

@Component
public class InsertRecord extends DbCommand {
    @Autowired
    private DataBaseManager dataBaseManager;

    InsertRecord() {
        super(CommandsHref.INSERT_RECORD);
    }

    @Override
    public void run(HttpServletRequest req, Connection connection) {
        String tableName = req.getParameter("table_name");
        String[] columns = req.getParameterValues("insert_columns[]");
        String[] values = req.getParameterValues("insert_values[]");
        dataBaseManager.insertRecord(connection, tableName, columns, values);
    }
}
