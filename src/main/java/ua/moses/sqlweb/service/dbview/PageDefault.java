package ua.moses.sqlweb.service.dbview;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

@Component
public class PageDefault extends DbView {

    public PageDefault() {
        super(ViewHref.DEFAULT);
    }


}
