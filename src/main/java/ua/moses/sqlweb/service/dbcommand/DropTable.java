package ua.moses.sqlweb.service.dbcommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.moses.sqlweb.model.DataBaseManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;

@Component
public class DropTable extends DbCommand {
    @Autowired
    private DataBaseManager dataBaseManager;

    DropTable() {
        super(CommandsHref.DROP_TABLE);
    }

    @Override
    public void run(HttpServletRequest req, Connection connection) {
        String tableName = req.getParameter("table_name");
        dataBaseManager.dropTable(connection, tableName);
    }
}
