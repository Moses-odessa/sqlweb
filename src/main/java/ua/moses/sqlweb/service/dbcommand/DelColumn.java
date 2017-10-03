package ua.moses.sqlweb.service.dbcommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.moses.sqlweb.model.DataBaseManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;

@Component
public class DelColumn extends DbCommand {
    @Autowired
    private DataBaseManager dataBaseManager;

    DelColumn() {
        super(CommandsHref.DEL_COLUMN);
    }

    @Override
    public void run(HttpServletRequest req, Connection connection) {
        String tableName = req.getParameter("table_name");
        String columnName = req.getParameter("column_name");
        dataBaseManager.delColumn(connection, tableName, columnName);
    }
}
