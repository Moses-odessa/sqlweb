package ua.moses.sqlweb.service.dbcommand;

public enum CommandsHref {
    CONNECT("Connect", "connect"),
    CREATE_TABLE("Create table", "create_table"),
    DROP_TABLE("Delete table", "delete"),
    CLEAR_TABLE("Clear table", "clear"),
    ADD_COLUMN("Add column", "add_column"),
    DEL_COLUMN("Del column", "del_column"),
    INSERT_RECORD("Insert record", "insert_record"),
    DEL_RECORD("Delete record", "del_record"),
    UPDATE_RECORD("Update record", "update_record");

    private String title;

    public String getLink() {
        return link;
    }

    private String link;

    CommandsHref(String title, String link) {
        this.title = title;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }
}
