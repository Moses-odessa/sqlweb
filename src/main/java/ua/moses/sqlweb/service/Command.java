package ua.moses.sqlweb.service;

import java.util.ArrayList;
import java.util.List;

public enum Command {
    HELP("Description", "default", true),
    CONNECT("Connect", "connect", true),
    GET_TABLES("List of the tables", "tables", true),
    CREATE_TABLE("Create table", "create", false),
    DROP_TABLE("Delete table", "delete", false),
    CLEAR_TABLE("Clear table", "clear", false),
    VIEW_TABLE_DATA("View table data", "view", false),
    ADD_COLUMN("Add column", "add_column", false),
    DEL_COLUMN("Del column", "del_column", false),
    INSERT("Insert record", "insert", false),
    DEL_RECORD("Delete record", "del_record", false),
    ERROR("Error page", "error", false);

    private MenuItem menuItem;
    private boolean enable;

    Command(String title, String link, boolean enable) {
        this.menuItem = new MenuItem(title, link, this);
        this.enable = enable;
    }

    public static List<MenuItem> getMenu() {
        List<MenuItem> result = new ArrayList<>(Command.values().length);
        for (Command item : Command.values()) {
            if (item.enable)result.add(item.getMenuItem());
        }
        return result;
    }

    public static MenuItem getMenuItemByLink(String link) {
        for (Command item : Command.values()) {
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
