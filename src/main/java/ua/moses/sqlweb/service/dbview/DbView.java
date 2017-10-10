package ua.moses.sqlweb.service.dbview;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import ua.moses.sqlweb.service.dbcommand.DbCommand;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public abstract class DbView {
    private ViewHref viewHref;
    private static List<DbView> dbViews = new ArrayList<>();

    @PostConstruct
    private void init() {
        try {
            DbView.getDbView(this.getHref().getLink());
        } catch (Exception e) {
            dbViews.add(this);
        }
    }

    public DbView(ViewHref viewHref) {
        this.viewHref = viewHref;
    }


    public void set(Model model, HttpSession session) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        model.mergeAttributes(parameters);
        String command = (String) parameters.get("command");
        Connection connection = (Connection) session.getAttribute("db_connection");
        if (command != null && !command.isEmpty()) {
            DbCommand currentCommand = DbCommand.getCommand(command);
            currentCommand.run(model, connection, session);
        }
        model.addAttribute("current_page", getHref());
    }

    public ViewHref getHref() {
        return this.viewHref;
    }



    public static DbView getDbView(String action) throws Exception {
        String viewName = action;
        if (viewName == null || viewName.isEmpty()) {
            viewName = ViewHref.DEFAULT.getLink();
        }
        for (DbView dbView : dbViews) {
            if (dbView.getHref().getLink().equals(viewName)) {
                return dbView;
            }
        }
        throw new Exception("Unknown Page!!!!");
    }
}