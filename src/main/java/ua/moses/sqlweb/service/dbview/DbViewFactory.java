package ua.moses.sqlweb.service.dbview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class DbViewFactory {
    private List<DbView> dbViews;

    @Autowired
    public void setDbViews(List<DbView> dbViews) {
        this.dbViews = dbViews;
    }


    public DbView getDbView(String action) throws Exception {
        String viewName = action;
        if (viewName == null || viewName.isEmpty()){
            viewName = ViewHref.DEFAULT.getLink();
        }
        for (DbView dbView : dbViews) {
            if (dbView.getHref().getLink().equals(viewName)) {
                return dbView;
            }
        }
        throw new Exception("Unknown Page!!!!");
    }

    public List<ViewHref> getMenu() {
        List<ViewHref> result = new ArrayList<>();
        for (DbView dbView : dbViews) {
            if (dbView.getHref().getOrder() >= 0){
                result.add(dbView.getHref());
            }
        }
        result.sort(Comparator.comparingInt(ViewHref::getOrder));
        return result;
    }
}
