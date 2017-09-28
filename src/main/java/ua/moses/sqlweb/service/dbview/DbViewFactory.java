package ua.moses.sqlweb.service.dbview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DbViewFactory {
    private @Autowired List<DbView> dbViewList;
    private @Autowired DbView[] dbViewList1;

    public DbViewFactory(List<DbView> dbViewList, DbView[] dbViewList1) {
        this.dbViewList = dbViewList;
        this.dbViewList1 = dbViewList1;
    }

    public List<DbView> getDbViewList() {
        return dbViewList;
    }

    public void setDbViewList(List<DbView> dbViewList) {
        this.dbViewList = dbViewList;
    }

    public DbView getDbView(String action) {

        return null;
    }

    public List<Href> getMenu(String action) {
        List<Href> result = new ArrayList<>();
        for (DbView dbView: dbViewList){
            result.add(dbView.getHref());
        }
        return result;
    }
}
