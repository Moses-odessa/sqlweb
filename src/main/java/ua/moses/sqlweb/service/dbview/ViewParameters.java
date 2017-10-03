package ua.moses.sqlweb.service.dbview;

public enum ViewParameters {
    TABLE_NAME("Table name", "table_name"),
    DB_NAME("Database name", "db_name"),
    DB_USER("User name", "user_name"),
    DB_PASSWORD("User password", "user_password");

    private String title;

    private String link;

    ViewParameters(String title, String link) {
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
