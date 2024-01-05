package com.cat201.dao;

import java.sql.Connection;
import java.util.List;

import com.cat201.beans.Staff;

public class authDAO {

    private StaffDAO staffDAO = new StaffDAO();

    public authDAO(Connection conn) {
        this.staffDAO = new StaffDAO(conn);
    }

    public boolean login(String email, String password) {
        String query= "WHERE email = '" + email + "' AND password = '" + password + "'";
        List<Staff> staff = staffDAO.getStaffQuery(query);
        if(staff != null && staff.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean register(String firstName, String lastName, String phone, String address, String postalCode, String email, String password) {
        Staff staff = new Staff(1, firstName, lastName, phone, address, postalCode, email, password);
        return staffDAO.addStaff(staff);
    }
}
