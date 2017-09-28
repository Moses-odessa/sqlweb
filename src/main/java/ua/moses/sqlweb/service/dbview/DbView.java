package ua.moses.sqlweb.service.dbview;

import org.springframework.stereotype.Component;
import ua.moses.sqlweb.service.dbcommand.DbCommandFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;

@Component
public abstract class DbView {
    String title;
    String link;
    int order;

    public DbView(String title, String link, int order) {
        this.title = title;
        this.link = link;
        this.order = order;
    }

    public void setVariables(HttpServletRequest req, Connection connection) {
        String command = req.getParameter("command");
        if (command != null && !command.isEmpty()) {
            new DbCommandFactory().getCommand(command).run(req, connection);
        }
    }

    public Href getHref() {
        return new Href(this.title, this.link);
    }
}
