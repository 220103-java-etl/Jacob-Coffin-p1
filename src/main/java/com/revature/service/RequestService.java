package com.revature.service;

import com.revature.models.Request;
import com.revature.repository.RequestDAO;

public class RequestService {

    RequestDAO rdao = new RequestDAO();

    public Request add(Request r) {
        return rdao.add(r);
    }

    public Request getById(int id) {
        return rdao.getById(id);
    }

    public void update(Request r) {
        rdao.update(r);
    }
}
