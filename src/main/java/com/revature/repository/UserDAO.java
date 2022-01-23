package com.revature.repository;

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
        String sql = "insert into users values (default, ?, ?, ?) returning *";
        try(Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,user.getFirst_name());
            ps.setString(2,user.getLast_name());
            ps.setString(3,user.getRole());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                User u = new User(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("role")
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
                        rs.getString("role")
                );
                return u;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public User getByName(String first_name, String last_name) {
        String sql = "select * from users where first_name = ? and last_name = ?";
        try(Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "first_name");
            ps.setString(2, "last_name");
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                User u = new User(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("role")
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
                        rs.getString("role")
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
        String sql = "update users set first_name = ?, last_name = ? where id = ?";
        try(Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,user.getFirst_name());
            ps.setString(2,user.getLast_name());
            ps.setInt(3,user.getId());
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
