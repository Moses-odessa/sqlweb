package ua.moses.sqlweb.service;

import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public interface Service {
    void setMenuData(Model model);
    void setContentData(Model model, String action, HttpSession session);
}
