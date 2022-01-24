package com.revature.models;

import java.util.Objects;

public class Request {

    private int id;
    private String approval;
    private double refund;
    private int user_id;
    private int form_id;

    public Request() {}

    public Request(int id, String approval, double refund, int user_id, int form_id) {
        this.id = id;
        this.approval = approval;
        this.refund = refund;
        this.user_id = user_id;
        this.form_id = form_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    public double getRefund() {
        return refund;
    }

    public void setRefund(double refund) {
        this.refund = refund;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getForm_id() {
        return form_id;
    }

    public void setForm_id(int form_id) {
        this.form_id = form_id;
    }

    @Override
    public String toString() {
        return "Request [" +
                "id=" + id +
                ", approval='" + approval + '\'' +
                ", refund=" + refund +
                ", user_id=" + user_id +
                ", form_id=" + form_id +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Request)) return false;
        Request request = (Request) o;
        return id == request.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
