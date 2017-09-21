package ua.moses.sqlweb.service;

public class MenuItem {
    private String title;
    private String link;
    private MainMenu menuEnum;

    public MenuItem(String title, String link, MainMenu mainMenu) {
        this.title = title;
        this.link = link;
        this.menuEnum = mainMenu;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuItem menuItem = (MenuItem) o;

        if (title != null ? !title.equals(menuItem.title) : menuItem.title != null) return false;
        return link != null ? link.equals(menuItem.link) : menuItem.link == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (link != null ? link.hashCode() : 0);
        return result;
    }

    public MainMenu getMenuEnum() {
        return menuEnum;
    }
}
