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
import com.cat201.beans.Case;
import com.cat201.dao.CaseDAO;

import com.cat201.utils.Table;

public class CaseController {
    

    private Stage primaryStage;
    private CaseDAO caseDAO;
    private TableView<Case> tableView;

    private Pane mainContent;
    private Text title;

    public CaseController(Stage primaryStage, Connection conn) {
        this.primaryStage = primaryStage;
        caseDAO = new CaseDAO(conn);
        tableView = new TableView<>();
        mainContent = (Pane) primaryStage.getScene().lookup("#main_content");
        title = (Text) primaryStage.getScene().lookup("#main_title");
    }

    public void load() {
        title.setText("Cases List");
        
        // Fetch data from the database
        List<Case> cases = fetchData();
        
        // Add add button functionality
        Button addButton = (Button) primaryStage.getScene().lookup("#main_addbtn");
        addButton.setOnAction(e -> {
            changeToAddCase();
        });

        Case caseobj = new Case();
        ArrayList<String> colNames = caseobj.getColumnNames();
        ArrayList<String> cellData = caseobj.getCellData();

        // Generate table
        Table.generateTable(cases, tableView, mainContent, colNames, cellData);
    }

    private List<Case> fetchData() {
        return caseDAO.getCases();
    }

    private void changeToAddCase() {
        title.setText("Add Case");
        // hide the search bar and add button 
        TextField searchField = (TextField) primaryStage.getScene().lookup("#main_searchinput");
        searchField.setVisible(false);
        Button addButton = (Button) primaryStage.getScene().lookup("#main_addbtn");
        addButton.setVisible(false);
        Button searchButton = (Button) primaryStage.getScene().lookup("#main_searchbtn");
        searchButton.setVisible(false);
    
        Case Case = new Case();
        ArrayList<String> colNames = Case.getColumnNames();
        // ArrayList<String> cellData = Case.getCellData();

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

