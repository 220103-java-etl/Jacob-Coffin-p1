package com.revature.app;

import com.revature.models.User;
import com.revature.repository.UserDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.revature.util.ConnectionUtil.getConnectionUtil;

public class AppDriver {

    public static void main(String[] args) {

        Connection conn = getConnectionUtil().getConnection();
        Scanner scan = new Scanner(System.in);
        String fname;
        String lname;
        String role;
        int input = 0;
        int input2 = 0;
        UserDAO udao = new UserDAO();

        System.out.println("Welcome to the Test App!");
        do {
            System.out.println("Enter 1 to add user, enter 2 to delete, enter 3 to look up by ID, enter 4 to look up by name, enter 5 to print all, enter 6 to update, enter 0 to quit.");
            input = scan.nextInt();
            switch(input) {
                case 1:
                    System.out.println("Enter first name:");
                    fname = scan.next();
                    System.out.println("Enter last name:");
                    lname = scan.next();
                    System.out.println("Enter 1 for employee, enter 2 for finance manager:");
                    input2 = scan.nextInt();
                    if(input2 == 1) {
                        role = "Employee";
                    } else if(input2 == 2) {
                        role = "Finance Manager";
                    } else {
                        System.out.println("Invalid Input!");
                        break;
                    }
                    User u = new User(0, fname, lname, role);
                    u = udao.add(u);
                    System.out.println(u.toString());
                    break;
                case 2:
                    System.out.println("Enter ID of User to delete:");
                    input2 = scan.nextInt();
                    udao.delete(input2);
                    System.out.println("User " + input2 + " deleted.");
                    break;
                case 3:
                    System.out.println("Enter ID of User to look up:");
                    input2 = scan.nextInt();
                    u = udao.getById(input2);
                    System.out.println(u.toString());
                    break;
                case 4:
                    System.out.println("Enter first name:");
                    fname = scan.next();
                    System.out.println("Enter last name:");
                    lname = scan.next();
                    u = udao.getByName(fname, lname);
                    System.out.println(u.toString());
                    break;
                case 5:
                    List<User> users = udao.getAll();
                    for(User user : users) {
                        System.out.println(user.toString());
                    }
                    break;
                case 6:
                    System.out.println("Enter ID of user to update:");
                    input2 = scan.nextInt();
                    System.out.println("Enter new first name:");
                    fname = scan.next();
                    System.out.println("Enter new last name:");
                    lname = scan.next();
                    u = new User(input2, fname, lname, null);
                    udao.update(u);
                    System.out.println("User " + input2 + " updated.");
                    break;
                case 0:
                    System.out.println("Thank you for using the app, goodbye!");
                    break;
                default:
                    System.out.println("Invalid Input!");
            }
        } while(input != 0);
    }
}
