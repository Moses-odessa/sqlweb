package ua.moses.sqlweb.service.dbcommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.moses.sqlweb.model.DataBaseManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;

@Component
public class ClearTable extends DbCommand {
    @Autowired
    private DataBaseManager dataBaseManager;

    ClearTable() {
        super(CommandsHref.CLEAR_TABLE);
    }

    @Override
    public void run(HttpServletRequest req, Connection connection) {
        String tableName = req.getParameter("table_name");
        dataBaseManager.clearTable(connection, tableName);
    }
}