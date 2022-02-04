package com.revature.app;

import com.revature.models.R_form;
import com.revature.models.Request;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.repository.FormDAO;
import com.revature.repository.RequestDAO;
import com.revature.repository.UserDAO;
import com.revature.service.FormService;
import com.revature.service.RequestService;
import com.revature.service.UserService;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import static com.revature.util.ConnectionUtil.getConnectionUtil;

public class AppDriver {

    public static void main(String[] args) {

        Connection conn = getConnectionUtil().getConnection();
        Scanner scan = new Scanner(System.in);
        String fname;
        String lname;
        String username;
        String pass;
        String email;
        Role role;
        int input = 0;
        int input2 = 0;
        UserDAO udao = new UserDAO();
        User u = new User();
        UserService us = new UserService();

        if(conn != null) {
            System.out.println("Connection Successful");
        } else {
            System.out.println("Something went wrong");
        }

        System.out.println("Welcome to the Employee Refund App!");
        do {
            System.out.println("Enter 1 to login, enter 2 to register, enter 0 to quit.");
            input = scan.nextInt();
            switch(input) {
                case 1:
                    System.out.println("Enter username: ");
                    username = scan.next();
                    System.out.println("Enter password: ");
                    pass = scan.next();
                    u = us.login(username, pass);
                    if(u != null) {
                        userMenu(u);
                    }
                    break;
                case 2:
                    System.out.println("Enter username: ");
                    username = scan.next();
                    System.out.println("Enter password: ");
                    pass = scan.next();
                    System.out.println("Enter first name: ");
                    fname = scan.next();
                    System.out.println("Enter last name: ");
                    lname = scan.next();
                    System.out.println("Enter email address: ");
                    email = scan.next();
                    System.out.println("Are you an employee or finance manager? Press 1 for employee, 2 for finance manager: ");
                    input2 = scan.nextInt();
                    if(input2 == 1) {
                        role = Role.EMPLOYEE;
                        u.setUsername(username);
                        u.setPass(pass);
                        u.setFirst_name(fname);
                        u.setLast_name(lname);
                        u.setEmail(email);
                        u.setRole(role);
                        u = udao.add(u);
                        System.out.println(u.toString());
                    } else if(input2 == 2) {
                        role = Role.FINANCE_MANAGER;
                        u.setUsername(username);
                        u.setPass(pass);
                        u.setFirst_name(fname);
                        u.setLast_name(lname);
                        u.setEmail(email);
                        u.setRole(role);
                        u = udao.add(u);
                        System.out.println(u.toString());
                    } else {
                        System.out.println("Invalid Input!");
                    }
                    break;
                case 0:
                    System.out.println("Thank you for using the app, goodbye!");
                    break;
                default:
                    System.out.println("Invalid Input!");
            }
        } while(input != 0);

        try {
            conn.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }



    public static void userMenu(User u) {

        Scanner scan = new Scanner(System.in);
        Role role = u.getRole();
        int input = 0;
        int input2 = 0;
        List<R_form> forms = new ArrayList<R_form>();
        FormDAO fdao = new FormDAO();
        RequestDAO rdao = new RequestDAO();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat tf = new SimpleDateFormat("HH:mm:ss");
        FormService fs = new FormService();
        UserService us = new UserService();
        RequestService rs = new RequestService();

        if(role == Role.EMPLOYEE) {
            System.out.println("Welcome " + u.getFirst_name());
            do {
                System.out.println("Enter 1 to view your Refund Forms, enter 2 to apply for Refund, enter 3 to edit Refund Form(s), enter 0 to exit.");
                input = scan.nextInt();
                switch(input) {
                    case 1:
                        forms = fdao.getByUserId(u.getId());
                        for(R_form form : forms) {
                            System.out.println(form.toString());
                        }
                        break;
                    case 2:
                        R_form f = new R_form();
                        System.out.println("Enter date of the event in the following format: 'YYYY-MM-DD'");
                        String sday = scan.next();
                        Date day = Date.valueOf(sday);
                        /*
                        try {
                            String sday = scan.next();
                            Date day = (Date) df.parse(sday);
                            f.setDay(day);
                        } catch(ParseException e) {
                            System.out.println("Please enter a valid date!");
                            break;
                        }
                         */
                        System.out.println("Enter time of the event in the following format: 'HH:MM:SS'");
                        String shour = scan.next();
                        Time hour = Time.valueOf(shour);
                        /*
                        try {
                            String shour = scan.next();
                            Time hour = (Time) df.parse(shour);
                            f.setHour(hour);
                        } catch(ParseException e) {
                            System.out.println("Please enter a valid time!");
                            break;
                        }
                         */
                        scan.nextLine();
                        System.out.println("Enter address:");
                        String address = scan.nextLine();
                        f.setAddress(address);
                        System.out.println("Enter city: ");
                        String city = scan.nextLine();
                        f.setCity(city);
                        System.out.println("Enter state two letter code: ");
                        String state = scan.next();
                        if(state.length() == 2) {
                            f.setState(state);
                        } else {
                            System.out.println("Please enter two letters exactly!");
                            break;
                        }
                        System.out.println("Enter 5-digit zip: ");
                        int zip = scan.nextInt();
                        if(zip >= 10000 && zip <= 99999) {
                            f.setZip(zip);
                        } else {
                            System.out.println("Please enter 5 digits!");
                            break;
                        }
                        scan.nextLine();
                        System.out.println("Type in a description: ");
                        String description = scan.nextLine();
                        f.setDescription(description);
                        System.out.println("How much does it cost?");
                        double cost = scan.nextDouble();
                        f.setCost(cost);
                        scan.nextLine();
                        System.out.println("What is the grading format?");
                        String grading_format = scan.nextLine();
                        f.setGrading_format(grading_format);
                        System.out.println("What is the type of event?");
                        System.out.println("Press 1 for University Course, press 2 for Seminar, press 3 for Certification Preperation Class");
                        System.out.println("Press 4 for Certification, press 5 for Technical Training, press 6 for Other.");
                        String event;
                        input2 = scan.nextInt();
                        if(input2 == 1) {
                            event = "University Course";
                            f.setEvent(event);
                        } else if(input2 == 2) {
                            event = "Seminar";
                            f.setEvent(event);
                        } else if(input2 == 3) {
                            event = "Certification Preperation Class";
                            f.setEvent(event);
                        } else if(input2 == 4) {
                            event = "Certification";
                            f.setEvent(event);
                        } else if(input2 == 5) {
                            event = "Technical Training";
                            f.setEvent(event);
                        } else if(input2 == 6) {
                            event = "Other";
                            f.setEvent(event);
                        } else {
                            System.out.println("Invalid Input!");
                        }
                        scan.nextLine();
                        System.out.println("Enter any justification (optional): ");
                        String justification = scan.nextLine();
                        f.setJustification(justification);
                        f.setUser_id(u.getId());
                        f = fs.addForm(f);
                        System.out.println(f.toString());
                        break;
                    case 3:
                        forms = fdao.getByUserId(u.getId());
                        List<Integer> ids = new ArrayList<Integer>();
                        for(R_form form : forms) {
                            ids.add(form.getId());
                            System.out.println(form.toString());
                        }
                        System.out.println("Enter ID of Form you want to edit: ");
                        input2 = scan.nextInt();
                        if(ids.contains(input2)) {
                            f = fs.getById(input2);
                            editForm(u, f);
                        } else {
                            System.out.println("Invalid Form ID!");
                        }
                        break;
                    case 0:
                        System.out.println("Thank you, " + u.getFirst_name());
                        break;
                    default:
                        System.out.println("Invalid Input!");
                }
            } while(input != 0);

        } else if(role == Role.FINANCE_MANAGER) {
            System.out.println("Welcome " + u.getFirst_name());
            do {
                System.out.println("Enter 1 to view Refund Forms, enter 2 to Select Refund Forms, enter 3 to Approve/Deny Refund, enter 0 to exit.");
                input = scan.nextInt();
                switch(input) {
                    case 1:
                        forms = fdao.getAll();
                        for(R_form form : forms) {
                            System.out.println(form.toString());
                        }
                        break;
                    case 2:
                        forms = fdao.getAll();
                        List<Integer> ids = new ArrayList<Integer>();
                        for(R_form form : forms) {
                            ids.add(form.getId());
                            System.out.println(form.toString());
                        }
                        System.out.println("Enter ID of Form you want to select for managing (Press 0 to exit).");
                        input2 = scan.nextInt();
                        if(ids.contains(input2)) {
                            R_form f = fs.getById(input2);
                            User u2 = us.getById(f.getUser_id());
                            double refund = refundMath(f.getEvent(), u2.getFunds(), f.getCost());
                            Request r = new Request();
                            r.setRefund(refund);
                            r.setUser_id(u.getId());
                            r.setForm_id(f.getId());
                            r = rs.add(r);
                            System.out.println(r.toString());
                        } else if(input == 0) {
                            System.out.println("Form selection exited.");
                        } else {
                            System.out.println("Invalid Form ID!");
                        }
                        break;
                    case 3:
                        List<Request> requests = rdao.getByUserId(u.getId());
                        List<R_form> forms2 = new ArrayList<R_form>();
                        R_form form = new R_form();
                        List<Integer> ids2 = new ArrayList<Integer>();
                        for(Request request : requests) {
                            form = fs.getById(request.getForm_id());
                            forms2.add(form);
                            ids2.add(request.getId());
                            System.out.println(request.toString());
                        }
                        for(R_form f : forms2) {
                            System.out.println(f.toString());
                        }
                        System.out.println("Select Request ID to Approve or Deny (Press 0 to exit)");
                        input2 = scan.nextInt();
                        if(ids2.contains(input2)) {
                            Request r = rs.getById(input2);
                            System.out.println("Press 1 to Approve, press 2 to Deny: ");
                            input2 = scan.nextInt();
                            if(input2 == 1) {
                                r.setApproval("Approved");
                                rs.update(r);
                                r = rs.getById(r.getId());
                                System.out.println(r.toString());
                            } else if(input2 == 2) {
                                r.setApproval("Denied");
                                rs.update(r);
                                r = rs.getById(r.getId());
                                System.out.println(r.toString());
                            } else {
                                System.out.println("Invalid Input!");
                            }
                        } else if(input == 0) {
                            System.out.println("Approval/Denial process exited.");
                        } else {
                            System.out.println("Invalid Request ID!");
                        }
                        break;
                    case 0:
                        System.out.println("Thank you, " + u.getFirst_name());
                        break;
                    default:
                        System.out.println("Invalid Input!");
                }
            } while(input != 0);

        } else {
            System.out.println("Error! Invalid Role!");
        }
    }



    public static void editForm(User u, R_form f) {

        Scanner scan = new Scanner(System.in);
        int input = 0;
        FormService fs = new FormService();
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        DateFormat tf = new SimpleDateFormat("HH:mm:ss");

        System.out.println("Enter 1 to edit date, enter 2 to edit time, enter 3 to edit address, enter 4 to edit city, enter 5 to edit state.");
        System.out.println("Enter 6 to edit zip, enter 7 to edit description, enter 8 to edit cost, enter 9 to edit grading format.");
        System.out.println("Enter 10 to edit type of event, enter 11 to edit justification, enter 12 to edit grade, enter 0 to cancel.");
        input = scan.nextInt();
        switch(input) {
            case 1:
                System.out.println("Enter date of the event in the following format: 'YYYY/MM/DD'");
                try {
                    String sday = scan.next();
                    Date day = (Date) df.parse(sday);
                    f.setDay(day);
                    fs.update(f);
                    f = fs.getById(f.getId());
                    System.out.println(f.toString());
                } catch(ParseException e) {
                    System.out.println("Please enter a valid date!");
                }
                break;
            case 2:
                System.out.println("Enter time of the event in the following format: 'HH:MM:SS'");
                try {
                    String shour = scan.next();
                    Time hour = (Time) df.parse(shour);
                    f.setHour(hour);
                    fs.update(f);
                    f = fs.getById(f.getId());
                    System.out.println(f.toString());
                } catch(ParseException e) {
                    System.out.println("Please enter a valid time!");
                }
                break;
            case 3:
                System.out.println("Enter address:");
                String address = scan.nextLine();
                f.setAddress(address);
                fs.update(f);
                f = fs.getById(f.getId());
                System.out.println(f.toString());
                break;
            case 4:
                System.out.println("Enter city: ");
                String city = scan.nextLine();
                f.setCity(city);
                fs.update(f);
                f = fs.getById(f.getId());
                System.out.println(f.toString());
                break;
            case 5:
                System.out.println("Enter state two letter code: ");
                String state = scan.next();
                if(state.length() == 2) {
                    f.setState(state);
                    fs.update(f);
                    f = fs.getById(f.getId());
                    System.out.println(f.toString());
                } else {
                    System.out.println("Please enter two letters exactly!");
                }
                break;
            case 6:
                System.out.println("Enter 5-digit zip: ");
                int zip = scan.nextInt();
                if(zip >= 10000 && zip <= 99999) {
                    f.setZip(zip);
                    fs.update(f);
                    f = fs.getById(f.getId());
                    System.out.println(f.toString());
                } else {
                    System.out.println("Please enter 5 digits!");
                }
                break;
            case 7:
                scan.nextLine();
                System.out.println("Type in a description: ");
                String description = scan.nextLine();
                f.setDescription(description);
                fs.update(f);
                f = fs.getById(f.getId());
                System.out.println(f.toString());
                break;
            case 8:
                System.out.println("How much does it cost?");
                double cost = scan.nextDouble();
                f.setCost(cost);
                fs.update(f);
                f = fs.getById(f.getId());
                System.out.println(f.toString());
                break;
            case 9:
                scan.nextLine();
                System.out.println("What is the grading format?");
                String grading_format = scan.nextLine();
                f.setGrading_format(grading_format);
                fs.update(f);
                f = fs.getById(f.getId());
                System.out.println(f.toString());
                break;
            case 10:
                System.out.println("What is the type of event?");
                System.out.println("Press 1 for University Course, press 2 for Seminar, press 3 for Certification Preperation Class");
                System.out.println("Press 4 for Certification, press 5 for Technical Training, press 6 for Other.");
                String event;
                int input2 = scan.nextInt();
                if(input2 == 1) {
                    event = "University Course";
                    f.setEvent(event);
                    fs.update(f);
                    f = fs.getById(f.getId());
                    System.out.println(f.toString());
                } else if(input2 == 2) {
                    event = "Seminar";
                    f.setEvent(event);
                    fs.update(f);
                    f = fs.getById(f.getId());
                    System.out.println(f.toString());
                } else if(input2 == 3) {
                    event = "Certification Preperation Class";
                    f.setEvent(event);
                    fs.update(f);
                    f = fs.getById(f.getId());
                    System.out.println(f.toString());
                } else if(input2 == 4) {
                    event = "Certification";
                    f.setEvent(event);
                    fs.update(f);
                    f = fs.getById(f.getId());
                    System.out.println(f.toString());
                } else if(input2 == 5) {
                    event = "Technical Training";
                    f.setEvent(event);
                    fs.update(f);
                    f = fs.getById(f.getId());
                    System.out.println(f.toString());
                } else if(input2 == 6) {
                    event = "Other";
                    f.setEvent(event);
                    fs.update(f);
                    f = fs.getById(f.getId());
                    System.out.println(f.toString());
                } else {
                    System.out.println("Invalid Input!");
                }
                break;
            case 11:
                scan.nextLine();
                System.out.println("Enter any justification: ");
                String justification = scan.nextLine();
                f.setJustification(justification);
                fs.update(f);
                f = fs.getById(f.getId());
                System.out.println(f.toString());
                break;
            case 12:
                scan.nextLine();
                System.out.println("Enter your grade: ");
                String grade = scan.nextLine();
                f.setGrade(grade);
                fs.update(f);
                f = fs.getById(f.getId());
                System.out.println(f.toString());
                break;
            case 0:
                System.out.println("Edit Aborted.");
                break;
            default:
                System.out.println("Invalid Input!");
        }
    }



    public static double refundMath(String event, double funds, double cost) {

        double refund = 0;

        if(event.equals("University Course")) {
            refund = cost * 0.80;
        } else if(event.equals("Seminar")) {
            refund = cost * 0.60;
        } else if(event.equals("Certification Preperation Class")) {
            refund = cost * 0.75;
        } else if(event.equals("Certification")) {
            refund = cost;
        } else if(event.equals("Technical Training")) {
            refund = cost * 0.90;
        } else if(event.equals("Other")) {
            refund = cost * 0.30;
        } else {
            System.out.println("INVALID EVENT TYPE!!");
        }
        if(funds < refund) {
            refund = funds;
        }
        return refund;
    }
}
