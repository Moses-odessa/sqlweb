package ua.moses.sqlweb.service.dbcommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import ua.moses.sqlweb.model.DataBaseManager;
import ua.moses.sqlweb.service.dbview.ViewParameters;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

@Component
public class Connect extends DbCommand {
    @Autowired
    private DataBaseManager dataBaseManager;

    public Connect() {
        super(CommandsHref.CONNECT);
    }

    @Override
    public void run(Model model, Connection connection, HttpSession session) {
        Map<String, Object> parameters = new HashMap<>();
        model.mergeAttributes(parameters);
        String databaseName = (String) parameters.get(ViewParameters.DB_NAME.getLink());
        String userName = (String) parameters.get(ViewParameters.DB_USER.getLink());
        String password = (String) parameters.get(ViewParameters.DB_PASSWORD.getLink());
        session.setAttribute("db_connection", dataBaseManager.connect(databaseName, userName, password));
    }
}
