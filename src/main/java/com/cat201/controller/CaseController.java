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
        // List<Case> cases = caseDAO.getCaseQuery(searchQuery);

        // Case caseobj = new Case();
        // ArrayList<String> colNames = caseobj.getColumnNames();
        // ArrayList<String> cellData = caseobj.getCellData();

        // // Generate table
        // Table.generateTable(cases, tableView, mainContent, colNames, cellData);
    }

    public void changeToAddCase() {
        Form.changeToAddForm(title, mainContent, primaryStage, colNames, cellData);
    }

    public void changeToDetailCase(Case caseobj) {
        Form.changeToDetailForm(caseobj, title, mainContent, primaryStage, colNames, this::updateAction, this::deleteAction);
    }

    private void updateAction(ArrayList<String> newdata) {
        // caseDAO.updateCase(caseobj);
    }

    private void deleteAction(Integer id) {
        // caseDAO.deleteCase(id);
    }
}

