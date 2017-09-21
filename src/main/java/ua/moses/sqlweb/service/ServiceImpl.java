package ua.moses.sqlweb.service;

import ua.moses.sqlweb.model.DataBaseManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ServiceImpl implements Service {
    @Override
    public DataBaseManager connect(String databaseName, String userName, String password) {
        throw new RuntimeException("Testing");
        //return null;
    }

    @Override
    public Map<String, Object> getAttributes(MenuItem menuItem) {
//        switch (MainMenu.getMenuItemByLink(menuItem.getLink()))
//            case MainMenu.CONNECT
        return null;
    }

    @Override
    public void setAttributes(HttpServletRequest req, MenuItem currentMenuItem) {
        req.setAttribute("menu", MainMenu.getMenu());
        switch (currentMenuItem.getMenuEnum()){
            case HELP: req.setAttribute("menu", MainMenu.getMenu());
        }

    }


}
