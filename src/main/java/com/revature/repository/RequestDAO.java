package com.revature.repository;

import com.revature.models.Request;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.revature.util.ConnectionUtil.getConnectionUtil;

public class RequestDAO implements GenericDAO<Request> {

    ConnectionUtil cu = getConnectionUtil();

    @Override
    public Request add(Request request) {
        String sql = "insert into requests values (default, ?, ?, ?, ?) returning *";
        try(Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,request.getApproval());
            ps.setDouble(2,request.getRefund());
            ps.setInt(3, request.getUser_id());
            ps.setInt(4,request.getForm_id());

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                Request r = new Request(
                        rs.getInt("id"),
                        rs.getString("approval"),
                        rs.getDouble("refund"),
                        rs.getInt("user_id"),
                        rs.getInt("form_id")
                );
                return r;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Request getById(Integer id) {
        String sql = "select * from requests where id = ?";
        try(Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                Request r = new Request(
                        rs.getInt("id"),
                        rs.getString("approval"),
                        rs.getDouble("refund"),
                        rs.getInt("user_id"),
                        rs.getInt("form_id")
                );
                return r;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Request> getByUserId(Integer id) {
        String sql = "select * from requests where user_id = ?";
        List<Request> requests = new ArrayList<Request>();
        try(Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Request r = new Request(
                        rs.getInt("id"),
                        rs.getString("approval"),
                        rs.getDouble("refund"),
                        rs.getInt("user_id"),
                        rs.getInt("form_id")
                );
                requests.add(r);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }

    @Override
    public Request getByUsername(String username) {
        return null;
    }

    @Override
    public List<Request> getAll() {
        String sql = "select * from requests";
        List<Request> requests = new ArrayList<Request>();
        try(Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Request r = new Request(
                        rs.getInt("id"),
                        rs.getString("approval"),
                        rs.getDouble("refund"),
                        rs.getInt("user_id"),
                        rs.getInt("form_id")
                );
                requests.add(r);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }

    @Override
    public void update(Request request) {
        String sql = "update requests set approval = ?, refund = ? where id = ?";
        try(Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, request.getApproval());
            ps.setDouble(2, request.getRefund());
            ps.setInt(3,request.getId());
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "delete from requests where id = ?";
        try(Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
