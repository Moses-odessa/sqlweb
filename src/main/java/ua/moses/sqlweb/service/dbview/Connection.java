package ua.moses.sqlweb.service.dbview;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class Connection extends DbView {
    public Connection() {
        super("Connect to DB", "connect", 1);
    }

    @Override
    public void setVariables(HttpServletRequest req, java.sql.Connection connection) {
        super.setVariables(req, connection);
    }
}
