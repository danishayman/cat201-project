package com.cat201.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cat201.beans.Case;

public class CaseDAO {

    Connection conn;

    public CaseDAO(Connection conn) {
        this.conn = conn;

        String stmt = "CREATE TABLE IF NOT EXISTS Cases (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT, status TEXT, clientName TEXT, staffId INTEGER, FOREIGN KEY (staffId) REFERENCES Staffs(id));";
        try {
            PreparedStatement pstmt = conn.prepareStatement(stmt);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Case setRsToCase(ResultSet rs) {
        Case Case = new Case();
        try {
            Case.setId(rs.getInt("id"));
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Case;
    }

    public Case getCase(int id) {
        String stmt = "SELECT * FROM Cases WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(stmt)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            // Process the result set and create a Case object
            if (rs.next()) {
                Case Case = new Case();
                Case.toString();
                return Case;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<Case> getCaseQuery(String stmt) {
        try (PreparedStatement pstmt = conn.prepareStatement(stmt)) {
            ResultSet rs = pstmt.executeQuery();

            List<Case> CaseList = new ArrayList<>();
            if (rs.next()) {
                Case Case = new Case();
                Case = this.setRsToCase(rs);
                CaseList.add(Case);
            }
            return CaseList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Case> getCases() {
        String stmt = "SELECT * FROM Cases";
        try (PreparedStatement pstmt = conn.prepareStatement(stmt)) {
            ResultSet rs = pstmt.executeQuery();

            // Process the result set and create an array of Case objects
            List<Case> CaseList = new ArrayList<>();
            while (rs.next()) {
                Case Case = new Case();
                Case = this.setRsToCase(rs);
                CaseList.add(Case);
            }
            return CaseList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addCase(Case Case) {
        String stmt = "INSERT INTO Cases (title, description, status, clientName, staffId) VALUES (?, ?, ?, ?, ?)";
        try(PreparedStatement pstmt = conn.prepareStatement(stmt)) {
            pstmt.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCase(Case Case) {
        String stmt = "UPDATE Cases SET title = ?, description = ?, status = ?, clientName = ?, staffId = ? WHERE id = ?";
        try(PreparedStatement pstmt = conn.prepareStatement(stmt)) {
            pstmt.setInt(9, Case.getId());
            pstmt.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCase(int id) {
        String stmt = "DELETE FROM Cases WHERE id = ?";
        try(PreparedStatement pstmt = conn.prepareStatement(stmt)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
