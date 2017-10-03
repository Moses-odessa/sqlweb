package ua.moses.sqlweb.service.dbview;

import org.springframework.stereotype.Component;
import ua.moses.sqlweb.service.dbcommand.CommandsHref;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.HashMap;

@Component
public class PageConnect extends DbView {
    public PageConnect() {
        super(ViewHref.CONNECTION);
    }

    @Override
    public void set(HttpServletRequest req) throws Exception {
        super.set(req);
        Connection connection = (Connection) req.getSession().getAttribute("db_connection");
        HashMap parameters = new HashMap();
        if (connection != null) {
                req.setAttribute("connected_user", connection.getMetaData().getUserName());
                req.setAttribute("connected_base", connection.getCatalog());

        }
        parameters.put("connected", connection != null);
        parameters.put("db_name", ViewParameters.DB_NAME);
        parameters.put("user_name", ViewParameters.DB_USER);
        parameters.put("user_password", ViewParameters.DB_PASSWORD);
        parameters.put("command_connect", CommandsHref.CONNECT);
        parameters.put("href_tables", ViewHref.TABLES);
        setVariables(req, parameters);
    }
}
