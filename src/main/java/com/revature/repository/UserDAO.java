package com.revature.repository;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.revature.util.ConnectionUtil.getConnectionUtil;

public class UserDAO implements GenericDAO<User> {

    ConnectionUtil cu = getConnectionUtil();

    @Override
    public User add(User user) {
        String sql = "insert into users values (default, ?, ?, ?, ?, ?, default, ?) returning *";
        try(Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,user.getFirst_name());
            ps.setString(2,user.getLast_name());
            ps.setString(3,user.getUsername());
            ps.setString(4,user.getPass());
            ps.setString(5,user.getEmail());
            ps.setString(6,user.getRole().toString());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                User u = new User(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("username"),
                        rs.getString("pass"),
                        rs.getString("email"),
                        rs.getDouble("funds"),
                        Role.valueOf(rs.getString("role").toUpperCase().replace(" ", "_"))
                );
                return u;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public User getById(Integer id) {
        String sql = "select * from users where id = ?";
        try(Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                User u = new User(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("username"),
                        rs.getString("pass"),
                        rs.getString("email"),
                        rs.getDouble("funds"),
                        Role.valueOf(rs.getString("role").toUpperCase().replace(" ", "_"))
                );
                return u;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public User getByUsername(String username) {
        String sql = "select * from users where username = ?";
        try(Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                User u = new User(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("username"),
                        rs.getString("pass"),
                        rs.getString("email"),
                        rs.getDouble("funds"),
                        Role.valueOf(rs.getString("role").toUpperCase().replace(" ", "_"))
                );
                return u;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<User> getAll() {
        String sql = "select * from users";
        List<User> users = new ArrayList<User>();
        try(Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                User u = new User(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("username"),
                        rs.getString("pass"),
                        rs.getString("email"),
                        rs.getDouble("funds"),
                        Role.valueOf(rs.getString("role").toUpperCase().replace(" ", "_"))
                );
                users.add(u);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    @Override
    public void update(User user) {
        String sql = "update users set first_name = ?, last_name = ?, username = ?, pass = ?, email = ?, funds = ? where id = ?";
        try(Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,user.getFirst_name());
            ps.setString(2,user.getLast_name());
            ps.setString(3,user.getUsername());
            ps.setString(4,user.getPass());
            ps.setString(5,user.getEmail());
            ps.setDouble(6,user.getFunds());
            ps.setInt(7,user.getId());
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void delete(Integer id) {
        String sql = "delete from users where id = ?";
        try(Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
