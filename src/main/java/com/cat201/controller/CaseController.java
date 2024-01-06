package com.cat201.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import com.cat201.beans.Case;

import com.cat201.dao.CaseDAO;

import com.cat201.utils.Table;

public class CaseController {
    
    @FXML
    private ScrollPane mainContent;

    private Stage primaryStage;
    private CaseDAO caseDAO;
    private TableView<Case> tableView;

    public CaseController(Stage primaryStage, Connection conn) {
        this.primaryStage = primaryStage;
        caseDAO = new CaseDAO(conn);
        tableView = new TableView<>();
    }

    public void loadTable() {
        
        // Fetch data from the database
        List<Case> cases = fetchData(); 

        // Get column names and cell data
        
        if(cases.size() == 0) {
            Pane main = (Pane) primaryStage.getScene().lookup("#main_content");
            main.getChildren().clear();
            main.getChildren().add(new Button("No data found"));
            return;
        }

        Case caseobj = cases.get(0);
        ArrayList<String> colNames = caseobj.getColumnNames();
        ArrayList<String> cellData = caseobj.getCellData();

        // Generate table
        Table.generateTable(cases, mainContent, tableView, primaryStage, colNames, cellData);
    }

    private List<Case> fetchData() {
        return caseDAO.getCases();
    }
}

