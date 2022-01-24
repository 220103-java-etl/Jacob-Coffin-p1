package com.revature.repository;

import com.revature.models.R_form;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.revature.util.ConnectionUtil.getConnectionUtil;

public class FormDAO implements GenericDAO<R_form> {

    ConnectionUtil cu = getConnectionUtil();

    @Override
    public R_form add(R_form r_form) {
        String sql = "insert into r_forms values (default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) returning *";
        try(Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1,r_form.getDay());
            ps.setTime(2,r_form.getHour());
            ps.setString(3,r_form.getAddress());
            ps.setString(4,r_form.getCity());
            ps.setString(5,r_form.getState());
            ps.setInt(6,r_form.getZip());
            ps.setString(7,r_form.getDescription());
            ps.setDouble(8,r_form.getCost());
            ps.setString(9,r_form.getGrading_format());
            ps.setString(10,r_form.getEvent());
            ps.setString(11,r_form.getJustification());
            ps.setString(12,r_form.getGrade());
            ps.setInt(13,r_form.getUser_id());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                R_form f = new R_form(
                        rs.getInt("id"),
                        rs.getDate("day"),
                        rs.getTime("hour"),
                        rs.getString("address"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getInt("zip"),
                        rs.getString("description"),
                        rs.getDouble("cost"),
                        rs.getString("grading_format"),
                        rs.getString("event"),
                        rs.getString("justification"),
                        rs.getString("grade"),
                        rs.getInt("user_id")
                );
                return f;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public R_form getById(Integer id) {
        String sql = "select * from r_forms where id = ?";
        try(Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                R_form f = new R_form(
                        rs.getInt("id"),
                        rs.getDate("day"),
                        rs.getTime("hour"),
                        rs.getString("address"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getInt("zip"),
                        rs.getString("description"),
                        rs.getDouble("cost"),
                        rs.getString("grading_format"),
                        rs.getString("event"),
                        rs.getString("justification"),
                        rs.getString("grade"),
                        rs.getInt("user_id")
                );
                return f;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public R_form getByUsername(String username) {
        return null;
    }

    @Override
    public List<R_form> getAll() {
        String sql = "select * from r_forms";
        List<R_form> forms = new ArrayList<R_form>();
        try(Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                R_form f = new R_form(
                        rs.getInt("id"),
                        rs.getDate("day"),
                        rs.getTime("hour"),
                        rs.getString("address"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getInt("zip"),
                        rs.getString("description"),
                        rs.getDouble("cost"),
                        rs.getString("grading_format"),
                        rs.getString("event"),
                        rs.getString("justification"),
                        rs.getString("grade"),
                        rs.getInt("user_id")
                );
                forms.add(f);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return forms;
    }

    @Override
    public void update(R_form r_form) {
        String sql = "update r_forms set day = ?, hour = ?, address = ?, city = ?, state = ?, zip = ?, description = ?, cost = ?, grading_format = ?, event = ?, justification = ?, grade = ? where id = ?";
        try(Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1,r_form.getDay());
            ps.setTime(2,r_form.getHour());
            ps.setString(3, r_form.getAddress());
            ps.setString(4,r_form.getCity());
            ps.setString(5,r_form.getState());
            ps.setInt(6,r_form.getZip());
            ps.setString(7,r_form.getDescription());
            ps.setDouble(8,r_form.getCost());
            ps.setString(9,r_form.getGrading_format());
            ps.setString(10,r_form.getEvent());
            ps.setString(11,r_form.getJustification());
            ps.setString(12,r_form.getGrade());
            ps.setInt(13,r_form.getId());
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "delete from r_forms where id = ?";
        try(Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
