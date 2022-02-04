package com.revature.service;

import com.revature.models.R_form;
import com.revature.repository.FormDAO;

import java.util.List;

public class FormService {

    FormDAO fdao = new FormDAO();

    public R_form getById(int id) {
        return fdao.getById(id);
    }

    public List<R_form> getByUserId(int id) {
        return fdao.getByUserId(id);
    }

    public R_form addForm(R_form f) {
        return fdao.add(f);
    }

    public void update(R_form f) {
        fdao.update(f);
    }
}
