package com.cat201.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import com.cat201.beans.Staff;

import com.cat201.dao.StaffDAO;

import com.cat201.utils.Table;

public class StaffController {
    
    @FXML
    private ScrollPane mainContent;

    private Stage primaryStage;
    private StaffDAO staffDAO;
    private TableView<Staff> tableView;

    public StaffController(Stage primaryStage, Connection conn) {
        this.primaryStage = primaryStage;
        staffDAO = new StaffDAO(conn);
        tableView = new TableView<>();
    }

    public void loadTable() {
        
        // Fetch data from the database
        List<Staff> staffs = fetchData(); 

        // Get column names and cell data
        Staff staff = staffs.get(0);
        ArrayList<String> colNames = staff.getColumnNames();
        ArrayList<String> cellData = staff.getCellData();

        // Generate table
        Table.generateTable(staffs, mainContent, tableView, primaryStage, colNames, cellData);
    }

    private List<Staff> fetchData() {
        return staffDAO.getStaffs();
    }
}
