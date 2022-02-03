package com.revature.app;

import com.revature.models.R_form;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.repository.FormDAO;
import com.revature.repository.UserDAO;
import com.revature.service.UserService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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

        System.out.println("Welcome to the Test App!");
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
                    System.out.println(u.toString());
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
        List<R_form> forms = new ArrayList<R_form>();
        FormDAO fdao = new FormDAO();

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
                        System.out.println("Enter day of the event in the following format: 'YYYY-MM-DD'");
                        String day = scan.next();
                        System.out.println("Enter time of the event in the following format: 'HH:MM:SS'");
                        String hour = scan.next();
                        scan.nextLine();
                        System.out.println("Enter address:");
                        String address = scan.nextLine();
                        System.out.println("Enter city: ");
                        String city = scan.nextLine();
                        System.out.println("Enter state two letter code: ");
                        String state = scan.next();
                        System.out.println("Enter zip: ");
                        int zip = scan.nextInt();
                        scan.nextLine();
                        System.out.println("Type in a description: ");
                        String description = scan.nextLine();
                        System.out.println("How much does it cost?");
                        double cost = scan.nextDouble();
                        scan.nextLine();
                        System.out.println("What is the grading format?");
                        String grading_format = scan.nextLine();
                        System.out.println("What is the type of event?");
                        String event = scan.nextLine();
                        System.out.println("Enter any justification (optional): ");
                        String justification = scan.nextLine();
                        break;
                    case 3:

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
                System.out.println("Enter 1 to view Refund Forms, enter 2 to Approve/Deny Refund, enter 0 to exit.");
                input = scan.nextInt();
                switch(input) {
                    case 1:
                        forms = fdao.getAll();
                        for(R_form form : forms) {
                            System.out.println(form.toString());
                        }
                        break;
                    case 2:

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
}
