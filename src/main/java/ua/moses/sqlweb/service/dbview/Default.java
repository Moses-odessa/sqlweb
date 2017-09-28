package ua.moses.sqlweb.service.dbview;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;

@Component
public class Default extends DbView {


    public Default() {
        super("Description", "default", 0);
    }


    @Override
    public void setVariables(HttpServletRequest req, Connection connection) {
        super.setVariables(req, connection);
    }


}
