package ua.moses.sqlweb.service.dbview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.moses.sqlweb.service.dbcommand.DbCommand;
import ua.moses.sqlweb.service.dbcommand.DbCommandFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.HashMap;

@Component
public abstract class DbView {
    private ViewHref viewHref;
    @Autowired
    private DbCommandFactory dbCommandFactory;

    public DbView(ViewHref viewHref) {
        this.viewHref = viewHref;
    }

    public void set(HttpServletRequest req) throws Exception {
        String command = req.getParameter("command");
        Connection connection = (Connection) req.getSession().getAttribute("db_connection");
        if (command != null && !command.isEmpty()) {
            DbCommand currentCommand = dbCommandFactory.getCommand(command);
                currentCommand.run(req, connection);
        }
        req.setAttribute("current_page", getHref());
    }

    public ViewHref getHref() {
        return this.viewHref;
    }


    protected void setVariables(HttpServletRequest req, HashMap<String, Object> parameters) {
        for (String key: parameters.keySet()){
            req.setAttribute(key, parameters.get(key));
        }

    }
}