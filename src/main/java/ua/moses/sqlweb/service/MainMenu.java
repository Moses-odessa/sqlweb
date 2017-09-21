package ua.moses.sqlweb.service;

import java.util.ArrayList;
import java.util.List;

public enum MainMenu {
    HELP("Help info", "default"),
    CONNECT("Connect", "connect"),
    GET_TABLES("List of the tables", "tables"),
    CREATE_TABLE("Create table", "create"),
    DROP_TABLE("Delete table", "delete"),
    CLEAR_TABLE("Clear table", "clear"),
    VIEW_TABLE_DATA("View table data", "view"),
    ERROR("Error page", "error");

    private MenuItem menuItem;

    MainMenu(String title, String link) {
        this.menuItem = new MenuItem(title, link, this);
    }

    public static List<MenuItem> getMenu() {
        List<MenuItem> result = new ArrayList<>(MainMenu.values().length);
        for (MainMenu item : MainMenu.values()) {
            if (item != MainMenu.ERROR)result.add(item.getMenuItem());
        }
        return result;
    }

    public static MenuItem getMenuItemByLink(String link) {
        for (MainMenu item : MainMenu.values()) {
            if (link.equals(item.getMenuItem().getLink())) {
                return item.getMenuItem();
            }
        }
        return null;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }
}
