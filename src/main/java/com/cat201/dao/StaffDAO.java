package com.cat201.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cat201.beans.Staff;

public class StaffDAO {

    Connection conn;
    
    public StaffDAO(Connection conn) {
        this.conn = conn;

        String stmt = "CREATE TABLE IF NOT EXISTS Staff (id INTEGER PRIMARY KEY AUTOINCREMENT,firstName TEXT,lastName TEXT,phone TEXT,address TEXT,postalCode TEXT,company TEXT,email TEXT UNIQUE,password TEXT)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(stmt);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Staff getStaff(int id) {
        String stmt = "SELECT * FROM Staffs WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(stmt)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            // Process the result set and create a Staff object
            if (rs.next()) {
                Staff Staff = new Staff();
                Staff.toString();
                return Staff;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public Staff getStaff(String email) {
        return null;
    }

    public boolean addStaff(Staff Staff) {
        String stmt = "INSERT INTO Staffs (firstName, lastName, phone, address, postalCode, company, email, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try(PreparedStatement pstmt = conn.prepareStatement(stmt)) {
            pstmt.setString(1, Staff.getFirstName());
            pstmt.setString(2, Staff.getLastName());
            pstmt.setString(3, Staff.getPhone());
            pstmt.setString(4, Staff.getAddress());
            pstmt.setString(5, Staff.getPostalCode());
            pstmt.setString(6, Staff.getCompany());
            pstmt.setString(7, Staff.getEmail());
            pstmt.setString(8, Staff.getPassword());
            pstmt.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateStaff(Staff Staff) {
        return false;
    }

    public boolean deleteStaff(Staff Staff) {
        return false;
    }

}
