package ua.moses.sqlweb.service.dbview;

public class Href {
    private String title;
    private String link;

    public Href(String title, String link) {
        this.title = title;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }
}
