package com.cat201.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import com.cat201.beans.Staff;

import com.cat201.dao.StaffDAO;

import com.cat201.utils.Table;

public class StaffController {
    
    private Stage primaryStage;
    private StaffDAO staffDAO;
    private TableView<Staff> tableView;

    private Pane mainContent;
    private Text title;

    public StaffController(Stage primaryStage, Connection conn) {
        this.primaryStage = primaryStage;
        staffDAO = new StaffDAO(conn);
        tableView = new TableView<>();
        mainContent = (Pane) primaryStage.getScene().lookup("#main_content");
        title = (Text) primaryStage.getScene().lookup("#main_title");
    }

    public void load() {
        title.setText("Staffs List");
        // Fetch data from the database
        List<Staff> staffs = fetchData(); 

        // Add add button functionality
        Button addButton = (Button) primaryStage.getScene().lookup("#main_addbtn");
        addButton.setOnAction(e -> {
            changeToAddStaff();
        });

        // Get column names and cell data
        Staff staff = new Staff();
        ArrayList<String> colNames = staff.getColumnNames();
        ArrayList<String> cellData = staff.getCellData();

        // Generate table
        Table.generateTable(staffs, tableView, mainContent, colNames, cellData);
    }

    private List<Staff> fetchData() {
        return staffDAO.getStaffs();
    }

    public void changeToAddStaff() {
        title.setText("Add Staff");
        // hide the search bar and add button 
        TextField searchField = (TextField) primaryStage.getScene().lookup("#main_searchinput");
        searchField.setVisible(false);
        Button addButton = (Button) primaryStage.getScene().lookup("#main_addbtn");
        addButton.setVisible(false);
        Button searchButton = (Button) primaryStage.getScene().lookup("#main_searchbtn");
        searchButton.setVisible(false);
    
        Staff staff = new Staff();
        ArrayList<String> colNames = staff.getColumnNames();
        // ArrayList<String> cellData = staff.getCellData();

        Pane addStaffForm = new Pane();

        // clear the main anchor pane
        mainContent.getChildren().clear();
    
        for(int i=0; i<colNames.size(); i++) {
            TextField textField = new TextField();
            textField.setPromptText(colNames.get(i));
            textField.setLayoutX(100);
            textField.setLayoutY(100 + i*50);
            addStaffForm.getChildren().add(textField);
        }

        mainContent.getChildren().add(addStaffForm);
    }


}
