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

import com.cat201.beans.Case;
import com.cat201.dao.CaseDAO;
import com.cat201.utils.Form;
import com.cat201.utils.Table;
import com.cat201.utils.Toast;
import com.cat201.utils.Utils;

public class CaseController {
    

    private Stage primaryStage;
    private CaseDAO caseDAO;
    private TableView<Case> tableView;

    private Pane mainContent;
    private Text title;

    private ArrayList<String> colNames;
    private ArrayList<String> cellData;

    public CaseController(Stage primaryStage, Connection conn, Pane mainContent) {
        this.primaryStage = primaryStage;
        this.mainContent = mainContent;
        caseDAO = new CaseDAO(conn);
        tableView = new TableView<>();
        title = (Text) primaryStage.getScene().lookup("#main_title");

        Case caseobj = new Case();
        colNames = caseobj.getColumnNames();
        cellData = caseobj.getCellData();
    }

    public void load() {
        title.setText("Cases List");
        Utils.get().show();
        
        // Fetch data from the database
        List<Case> cases = fetchData();

        // Search button functionality
        Button searchButton = (Button) primaryStage.getScene().lookup("#main_searchbtn");
        searchButton.setOnAction(e -> {
            searchData();
        });
        
        // Add button functionality
        Button addButton = (Button) primaryStage.getScene().lookup("#main_addbtn");
        addButton.setOnAction(e -> {
            changeToAddCase();
        });

        // Generate table
        Table.generateTable(cases, tableView, mainContent, colNames, cellData, this::changeToDetailCase);
    }

    private List<Case> fetchData() {
        return caseDAO.getCases();
    }

    private void searchData() {
        TextField searchField = (TextField) primaryStage.getScene().lookup("#main_searchinput");
        String searchQuery = searchField.getText();

        String stmt = "SELECT * FROM Cases " +
                    " WHERE title LIKE '%" + searchQuery + "%' " +
                    " OR description LIKE '%" + searchQuery + "%' " +
                    " OR status LIKE '%" + searchQuery + "%' " +
                    " OR clientID LIKE '%" + searchQuery + "%' " +
                    " OR staffID LIKE '%" + searchQuery + "%' ";
        List<Case> cases = caseDAO.getCaseQuery(stmt);

        Case caseobj = new Case();
        ArrayList<String> colNames = caseobj.getColumnNames();
        ArrayList<String> cellData = caseobj.getCellData();

        Table.generateTable(cases, tableView, mainContent, colNames, cellData, this::changeToDetailCase);
    }

    public void changeToAddCase() {
        Form.changeToAddForm(title, "Case", mainContent, primaryStage, colNames, cellData, this::addAction);
    }

    private void addAction(ArrayList<String> newdata) {
        // caseDAO.addCase(newdata);
    }

    public void changeToDetailCase(Case caseobj) {
        Form.changeToDetailForm(caseobj, title, "Case", mainContent, primaryStage, colNames, this::updateAction, this::deleteAction);
    }

    private void updateAction(ArrayList<String> newdata) {
        Case newCase = new Case();
        newCase.setID(Integer.parseInt(newdata.get(0)));
        newCase.setTitle(newdata.get(1));
        newCase.setDescription(newdata.get(2));
        newCase.setStatus(newdata.get(3));
        newCase.setClientId(newdata.get(4));
        newCase.setStaffId(newdata.get(5));
        boolean response = caseDAO.updateCase(newCase);
        if(response) {
            Toast.get().showSuccess("Case updated successfully");
        }else {
            Toast.get().showError("Error updating case");
        }
    }

    private void deleteAction(Integer id) {
        boolean response = caseDAO.deleteCase(id);
        if(response) {
            Toast.get().showSuccess("Case deleted successfully");
            load();
        }else {
            Toast.get().showError("Error deleting case");
        }
    }
}

