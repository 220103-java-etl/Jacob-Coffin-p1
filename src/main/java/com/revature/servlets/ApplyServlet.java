package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.R_form;
import com.revature.models.User;
import com.revature.service.FormService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ApplyServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        FormService fs = new FormService();
        ObjectMapper om = new ObjectMapper();
        System.out.println("doPost Method in apply reached");

        if(session == null) {
            System.out.println("Session null on apply");
        } else {
            System.out.println("Session not null on apply");
            User u = (User) session.getAttribute("user");
            /*
            List<R_form> uforms = fs.getByUserId(u.getId());
            for(R_form form : uforms) {
                System.out.println(form.toString());
            }
             */
            R_form r = om.readValue(req.getReader(), R_form.class);
            r.setUser_id(u.getId());
            r = fs.addForm(r);
            System.out.println(r.toString());
        }
    }
}
