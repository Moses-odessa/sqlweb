package ua.moses.sqlweb.service.dbcommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.moses.sqlweb.model.DataBaseManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;

@Component
public class Connect extends DbCommand {
    @Autowired
    private DataBaseManager dataBaseManager;

    public Connect() {
        super("connect");
    }

    @Override
    public void run(HttpServletRequest req, Connection connection) {
        String databaseName = req.getParameter("dbname");
        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        req.getSession().setAttribute("db_connection", dataBaseManager.connect(databaseName, userName, password));
    }
}
