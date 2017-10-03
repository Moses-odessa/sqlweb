package ua.moses.sqlweb.service.dbview;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public enum ViewHref {
    DEFAULT("Description", "default", 0),
    CONNECTION("Connect to DB", "connect", 1),
    TABLES("List of the tables", "tables", 2),
    TABLE_DATA("View table", "view", -1),
    EDIT_RECORD("Edit record", "edit", -1),
    ERROR("Error Page" , "error", -1);

    private String title;
    private String link;
    private int order;

    ViewHref(String title, String link, int order) {
        this.title = title;
        this.link = link;
        this.order = order;
    }

    public static List<ViewHref> getMenu() {
        List<ViewHref> result = new ArrayList<>();
        for (ViewHref href : ViewHref.values()) {
            if (href.getOrder() >= 0){
                result.add(href);
            }
        }
        result.sort(Comparator.comparingInt(ViewHref::getOrder));
        return result;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public int getOrder() {
        return order;
    }
}
