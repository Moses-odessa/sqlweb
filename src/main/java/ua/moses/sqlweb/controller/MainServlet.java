package ua.moses.sqlweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ua.moses.sqlweb.service.Command;
import ua.moses.sqlweb.service.MenuItem;
import ua.moses.sqlweb.service.Service;
import ua.moses.sqlweb.service.ServiceImpl;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MainServlet extends HttpServlet {

    @Autowired
    private Service service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = getAction(req);
        MenuItem currentMenuItem = Command.getMenuItemByLink(action);
        if (currentMenuItem == null) {
            currentMenuItem = Command.HELP.getMenuItem();
        }

        service.doGet(req, currentMenuItem);
        req.getRequestDispatcher("main.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = getAction(req);
        MenuItem currentMenuItem = Command.getMenuItemByLink(action);

        currentMenuItem = service.doPost(req, currentMenuItem);
        service.doGet(req, currentMenuItem);
        req.getRequestDispatcher("main.jsp").forward(req, resp);

    }

    private String getAction(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        return requestURI.substring(req.getContextPath().length() + 1, requestURI.length());
    }

}
