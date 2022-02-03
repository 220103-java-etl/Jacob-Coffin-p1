package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UserServlet extends HttpServlet {

    //ObjectMapper om = new ObjectMapper();
    //UserService us = new UserService();

/*
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        HttpSession session = req.getSession(false);
        PrintWriter out = resp.getWriter();

        if(session == null) {
            out.write("<center><br><br><h2>Invalid Username or Password!</h2></center>");
        } else {
            User u = (User) session.getAttribute("user");
            out.write("<h3>" + u.getUsername() + "</h3>");

        }
    }
*/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        HttpSession session = req.getSession(false);
        PrintWriter out = resp.getWriter();

        if(session == null) {
            out.write("<center><br><br><h2>Invalid Username or Password!</h2></center>");
        } else {
            User u = (User) session.getAttribute("user");
            out.write("<h3>" + u.getUsername() + "</h3>");

        }
    }

}
