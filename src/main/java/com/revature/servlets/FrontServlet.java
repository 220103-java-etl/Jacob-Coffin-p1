package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FrontServlet extends HttpServlet {

    UserService us = new UserService();
    ObjectMapper om = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String pass = req.getParameter("password");

        //resp.getWriter().write(username + "<br>" + pass);

        System.out.println("username: " + username);
        System.out.println("password: " + pass);

        User u = us.login(username, pass);
        StringBuilder uri = new StringBuilder(req.getRequestURI());
        System.out.println("URI: " + uri);

        uri.replace(0,req.getContextPath().length() + 1, "");
        int uid = 0;
        if(uri.indexOf("/") != -1) {
            uid = Integer.parseInt(uri.replace(0,uri.indexOf("/") + 1, "").toString());
        }
        System.out.println("Path is now: " + uri);

        if(uid == 0) {
            List<User> users = us.getAll();
            resp.getWriter().write(om.writeValueAsString(users));
        } else {
            System.out.println("User ID: " + uid);
            resp.getWriter().write(om.writeValueAsString(u));
        }

    }
}
