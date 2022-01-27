package com.revature.service;

import com.revature.models.User;
import com.revature.repository.UserDAO;

import java.util.List;

public class UserService {

    private UserDAO udao = new UserDAO();

    public User login(String username, String password) {
        User u = udao.getByUsername(username);
        if(u != null) {
            if(username.equals(u.getUsername()) && password.equals(u.getPass())) {
                return u;
            }
        }
        System.out.println("Credentials do not match!");
        return null;
    }

    public List<User> getAll() {
        return udao.getAll();
    }

    public User getByUsername(String username) {
        return udao.getByUsername(username);
    }
}
