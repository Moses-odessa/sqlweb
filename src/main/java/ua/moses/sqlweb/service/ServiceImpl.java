package ua.moses.sqlweb.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import ua.moses.sqlweb.service.dbcommand.DbCommand;
import ua.moses.sqlweb.service.dbview.DbView;
import ua.moses.sqlweb.service.dbview.ViewHref;

import javax.servlet.http.HttpSession;
import java.util.List;

@Component
public class ServiceImpl implements Service {

    @Autowired
    private List<DbView> dbViews;
    @Autowired
    private static List<DbCommand> dbCommands;

    @Override
    public void setMenuData(Model model) {
        model.addAttribute("menu", ViewHref.getMenu());
    }

    @Override
    public void setContentData(Model model, String action, HttpSession session) {
        try {
            DbView currentDbView = DbView.getDbView(action);
            currentDbView.set(model, session);
        } catch (Exception e) {
            model.addAttribute("current_page", ViewHref.ERROR);
            model.addAttribute("error_text", e.getMessage());
        }

    }

}
