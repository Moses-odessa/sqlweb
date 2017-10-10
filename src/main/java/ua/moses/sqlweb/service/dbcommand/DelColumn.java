package ua.moses.sqlweb.service.dbcommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import ua.moses.sqlweb.model.DataBaseManager;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

@Component
public class DelColumn extends DbCommand {
    @Autowired
    private DataBaseManager dataBaseManager;

    DelColumn() {
        super(CommandsHref.DEL_COLUMN);
    }

    @Override
    public void run(Model model, Connection connection, HttpSession session) {
        Map<String, Object> parameters = new HashMap<>();
        model.mergeAttributes(parameters);
        String tableName = (String) parameters.get("table_name");
        String columnName = (String) parameters.get("column_name");
        dataBaseManager.delColumn(connection, tableName, columnName);
    }
}
