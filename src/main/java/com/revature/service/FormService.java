package com.revature.service;

import com.revature.models.R_form;
import com.revature.repository.FormDAO;

import java.util.List;

public class FormService {

    FormDAO fdao = new FormDAO();

    public List<R_form> getByUserId(int id) {
        return fdao.getByUserId(id);
    }

    public R_form addForm(R_form r) {
        return fdao.add(r);
    }
}
