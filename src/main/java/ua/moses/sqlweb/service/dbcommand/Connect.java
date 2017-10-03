package ua.moses.sqlweb.service.dbcommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.moses.sqlweb.model.DataBaseManager;
import ua.moses.sqlweb.service.dbview.ViewParameters;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;

@Component
public class Connect extends DbCommand {
    @Autowired
    private DataBaseManager dataBaseManager;

    public Connect() {
        super(CommandsHref.CONNECT);
    }

    @Override
    public void run(HttpServletRequest req, Connection connection) {
        String databaseName = req.getParameter(ViewParameters.DB_NAME.getLink());
        String userName = req.getParameter(ViewParameters.DB_USER.getLink());
        String password = req.getParameter(ViewParameters.DB_PASSWORD.getLink());
        req.getSession().setAttribute("db_connection", dataBaseManager.connect(databaseName, userName, password));
    }
}
