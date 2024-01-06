package com.cat201.controller;

import java.sql.Connection;

import com.cat201.dao.StaffDAO;

public class StaffController {
    
    private StaffDAO staffDAO;

    public StaffController(Connection conn) {
        staffDAO = new StaffDAO(conn);
    }

    public void load() {
        // get all staff from database
        System.out.println("getting all staffs");
    }

    public void createEventListeners() {
        
    }


}
