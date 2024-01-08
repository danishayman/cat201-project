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
import com.cat201.utils.Form;
import com.cat201.utils.Table;
import com.cat201.utils.Toast;

public class StaffController {
    
    private Stage primaryStage;
    private StaffDAO staffDAO;
    private TableView<Staff> tableView;

    private Pane mainContent;
    private Text title;

    private ArrayList<String> colNames;
    private ArrayList<String> cellData;

    public StaffController(Stage primaryStage, Connection conn, Pane mainContent) {
        this.primaryStage = primaryStage;
        staffDAO = new StaffDAO(conn);
        tableView = new TableView<>();
        this.mainContent = mainContent;
        title = (Text) primaryStage.getScene().lookup("#main_title");

        // Get column names and cell data
        Staff staffobj = new Staff();
        colNames = staffobj.getColumnNames();
        cellData = staffobj.getCellData();
    }

    public void load() {
        title.setText("Staffs List");
        // Fetch data from the database
        List<Staff> staffs = fetchData(); 

        // Search button functionality
        Button searchButton = (Button) primaryStage.getScene().lookup("#main_searchbtn");
        searchButton.setOnAction(e -> {
            searchData();
        });

        // Add button functionality
        Button addButton = (Button) primaryStage.getScene().lookup("#main_addbtn");
        addButton.setOnAction(e -> {
            changeToAddStaff();
        });

        // Generate table
        Table.generateTable(staffs, tableView, mainContent, colNames, cellData, this::changeToDetailStaff);
    }

    private List<Staff> fetchData() {
        return staffDAO.getStaffs();
    }

    private void searchData() {
        TextField searchField = (TextField) primaryStage.getScene().lookup("#main_searchinput");
        String searchInput = searchField.getText();

        // If search input is empty, return
        if(searchInput.equals("")) return;

        String stmt = "SELECT * FROM Staffs " +
                     "WHERE firstName LIKE '%" + searchInput + "%' " +
                     "   OR lastName LIKE '%" + searchInput + "%' " +
                     "   OR phone LIKE '%" + searchInput + "%' " +
                     "   OR address LIKE '%" + searchInput + "%' " +
                     "   OR email LIKE '%" + searchInput + "%'";
        List<Staff> staffs = staffDAO.getStaffQuery(stmt);

        Staff staffobj = new Staff();
        ArrayList<String> colNames = staffobj.getColumnNames();
        ArrayList<String> cellData = staffobj.getCellData();

        // Generate table
        Table.generateTable(staffs, tableView, mainContent, colNames, cellData, this::changeToDetailStaff);
    }

    public void changeToAddStaff() {
        Form.changeToAddForm(title, mainContent, primaryStage, colNames, cellData);
    }

    public void changeToDetailStaff(Staff data) {
        Form.changeToDetailForm(data, title, mainContent, primaryStage, colNames, this::updateSubmit);
    }

    public void updateSubmit(ArrayList<String> newdata) {
        Staff newStaff = new Staff();
        newStaff.setID(Integer.parseInt(newdata.get(0)));
        newStaff.setFirstName(newdata.get(1));
        newStaff.setLastName(newdata.get(2));
        newStaff.setPhone(newdata.get(3));
        newStaff.setAddress(newdata.get(4));
        newStaff.setEmail(newdata.get(5));
        // System.out.println(newStaff);
        boolean response = staffDAO.updateStaff(newStaff);
        if(response) {
            Toast.get().showSuccess("Staff updated successfully");
        } else {
            Toast.get().showError("Error updating staff");
        }
    }
}
