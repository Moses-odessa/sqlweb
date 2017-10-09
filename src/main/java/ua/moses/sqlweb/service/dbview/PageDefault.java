package ua.moses.sqlweb.service.dbview;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PageDefault extends DbView {

    public PageDefault() {
        super(ViewHref.DEFAULT);
    }

    @Override
    public void set(HttpServletRequest req) throws Exception {
        super.set(req);
    }


}
