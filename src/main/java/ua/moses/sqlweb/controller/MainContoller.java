package ua.moses.sqlweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.moses.sqlweb.service.Service;

import javax.servlet.http.HttpSession;


@Controller
public class MainContoller {
    @Autowired
    private Service service;

    @RequestMapping(value = "/*", method = RequestMethod.GET)
    public String blanc() {
        return "redirect:/default";
    }

    @RequestMapping(value = "/{action}*", method = RequestMethod.GET)
    public String get(Model model, @PathVariable(value = "action") String action, HttpSession session) {
        service.setMenuData(model);
        service.setContentData(model, action, session);
        return "main";
    }

    @RequestMapping(value = "/{action}*", method = RequestMethod.POST)
    public String post(Model model, @PathVariable(value = "action") String action, HttpSession session) {
        service.setMenuData(model);
        service.setContentData(model, action, session);
        return "main";
    }

}
