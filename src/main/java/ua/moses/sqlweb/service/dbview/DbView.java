package ua.moses.sqlweb.service.dbview;

import org.springframework.stereotype.Component;
import ua.moses.sqlweb.service.dbcommand.DbCommand;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public abstract class DbView {
    private ViewHref viewHref;
    private static List<DbView> dbViews = new ArrayList<>();

    @PostConstruct
    private void init(){
        this.dbViews.add(this);
    }

    public DbView(ViewHref viewHref) {
        this.viewHref = viewHref;
    }


    public void setDbViews(List<DbView> dbViews) {
        this.dbViews = dbViews;
    }

    public void set(HttpServletRequest req) throws Exception {
        String command = req.getParameter("command");
        Connection connection = (Connection) req.getSession().getAttribute("db_connection");
        if (command != null && !command.isEmpty()) {
            DbCommand currentCommand = DbCommand.getCommand(command);
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

    public static DbView getDbView(String action) throws Exception {
        String viewName = action;
        if (viewName == null || viewName.isEmpty()){
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