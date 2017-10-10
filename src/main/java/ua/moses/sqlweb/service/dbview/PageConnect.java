package ua.moses.sqlweb.service.dbview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import ua.moses.sqlweb.service.dbcommand.CommandsHref;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component

public class PageConnect extends DbView {
    @Autowired
    private List<DbView> dbViews;

    public PageConnect() {
        super(ViewHref.CONNECTION);
    }

    @Override
    public void set(Model model, HttpSession session) throws Exception {
        super.set(model, session);
        Connection connection = (Connection) session.getAttribute("db_connection");
        Map<String, Object> parameters = new HashMap<>();
        if (connection != null) {
            parameters.put("connected_user", connection.getMetaData().getUserName());
            parameters.put("connected_base", connection.getCatalog());

        }
        parameters.put("connected", connection != null);
        parameters.put("db_name", ViewParameters.DB_NAME);
        parameters.put("user_name", ViewParameters.DB_USER);
        parameters.put("user_password", ViewParameters.DB_PASSWORD);
        parameters.put("command_connect", CommandsHref.CONNECT);
        parameters.put("href_tables", ViewHref.TABLES);
        model.mergeAttributes(parameters);
    }
}
