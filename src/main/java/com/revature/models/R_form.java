package com.revature.models;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class R_form {

    private int id;
    private Date day;
    private Time hour;
    private String address;
    private String city;
    private String state;
    private int zip;
    private String description;
    private double cost;
    private String grading_format;
    private String event;
    private String justification;

    public R_form() {}

    public R_form(int id, Date day, Time hour, String address, String city, String state, int zip, String description, double cost, String grading_format, String event, String justification) {
        this.id = id;
        this.day = day;
        this.hour = hour;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.description = description;
        this.cost = cost;
        this.grading_format = grading_format;
        this.event = event;
        this.justification = justification;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Time getHour() {
        return hour;
    }

    public void setHour(Time hour) {
        this.hour = hour;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getGrading_format() {
        return grading_format;
    }

    public void setGrading_format(String grading_format) {
        this.grading_format = grading_format;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    @Override
    public String toString() {
        return "R_form [" +
                "id=" + id +
                ", day=" + day +
                ", hour=" + hour +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip=" + zip +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", grading_format='" + grading_format + '\'' +
                ", event='" + event + '\'' +
                ", justification='" + justification + '\'' +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof R_form)) return false;
        R_form r_form = (R_form) o;
        return id == r_form.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
