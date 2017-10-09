package ua.moses.sqlweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.moses.sqlweb.service.Service;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainContoller {
    @Autowired
    private Service service;

    @RequestMapping(value = "/*", method = RequestMethod.GET)
    public String get(HttpServletRequest req) {
        service.setMenuData(req);
        service.setContentData(req);
        return "main";
    }

    @RequestMapping(value = "/*", method = RequestMethod.POST)
    public String post(HttpServletRequest req) {
        service.setMenuData(req);
        service.setContentData(req);
        return "main";
    }

}
