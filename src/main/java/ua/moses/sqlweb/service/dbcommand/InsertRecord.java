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
public class InsertRecord extends DbCommand {
    @Autowired
    private DataBaseManager dataBaseManager;

    InsertRecord() {
        super(CommandsHref.INSERT_RECORD);
    }

    @Override
    public void run(Model model, Connection connection, HttpSession session) {
        Map<String, Object> parameters = new HashMap<>();
        model.mergeAttributes(parameters);
        String tableName = (String) parameters.get("table_name");
        String[] columns = (String[]) parameters.get("insert_columns[]");
        String[] values = (String[]) parameters.get("insert_values[]");
        dataBaseManager.insertRecord(connection, tableName, columns, values);
    }
}
