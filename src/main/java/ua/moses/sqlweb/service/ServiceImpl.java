package ua.moses.sqlweb.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.moses.sqlweb.service.dbcommand.DbCommand;
import ua.moses.sqlweb.service.dbview.DbView;
import ua.moses.sqlweb.service.dbview.ViewHref;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
public class ServiceImpl implements Service {

    @Autowired
    private List<DbView> dbViews;
    @Autowired
    private static List<DbCommand> dbCommandsList;

    @Override
    public void setMenuData(HttpServletRequest req) {
        req.setAttribute("menu", ViewHref.getMenu());
    }

    @Override
    public void setContentData(HttpServletRequest req) {
        try {
            DbView currentDbView = DbView.getDbView(getAction(req));
            currentDbView.set(req);
        } catch (Exception e) {
            req.setAttribute("current_page", ViewHref.ERROR);
            req.setAttribute("error_text", e.getMessage());
        }

    }

    private String getAction(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        return requestURI.substring(req.getContextPath().length() + 1, requestURI.length());
    }
}
