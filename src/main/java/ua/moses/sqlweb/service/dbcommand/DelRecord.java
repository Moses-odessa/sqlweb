package ua.moses.sqlweb.service.dbcommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.moses.sqlweb.model.DataBaseManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;

@Component
public class DelRecord extends DbCommand {
    @Autowired
    private DataBaseManager dataBaseManager;

    DelRecord() {
        super(CommandsHref.DEL_RECORD);
    }

    @Override
    public void run(HttpServletRequest req, Connection connection) {
        String tableName = req.getParameter("table_name");
        String[] columns = req.getParameterValues("columns[]");
        String[] values = req.getParameterValues("values[]");
        dataBaseManager.delRecord(connection, tableName, columns, values);
    }
}
