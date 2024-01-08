package com.cat201.controller;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;

public class NavController {

    private Stage primaryStage;
    private Connection conn;
    private Pane mainContent;
    
    public NavController(Stage primaryStage, Connection conn) {
        this.primaryStage = primaryStage;
        this.conn = conn;
        this.mainContent = (Pane) primaryStage.getScene().lookup("#main_content");
    }

    public void createEventListeners() {
        showHome();

        Button navHome = (Button) primaryStage.getScene().lookup("#nav_home");
        Button navClient = (Button) primaryStage.getScene().lookup("#nav_clients");
        Button navStaff = (Button) primaryStage.getScene().lookup("#nav_staffs");
        Button navCase = (Button) primaryStage.getScene().lookup("#nav_cases");

        navHome.setOnAction(e-> {
            showHome();
        });

        navClient.setOnAction(e-> {
            showVisibility();
            ClientController clientController = new ClientController(primaryStage, conn, mainContent);
            clientController.load();
        });

        navStaff.setOnAction(e -> {
            showVisibility();
            StaffController staffController = new StaffController(primaryStage, conn, mainContent);
            staffController.load();
        });

        navCase.setOnAction(e -> {
            showVisibility();
            CaseController caseController = new CaseController(primaryStage, conn, mainContent);
            caseController.load();
        });
    }

    private void showHome() {
        Text title = (Text) primaryStage.getScene().lookup("#main_title");
        title.setText("Dashboard");
        // Hide search and add button in the homepage
        hideVisibility();
    }

    private void hideVisibility() {
        TextField searchField = (TextField) primaryStage.getScene().lookup("#main_searchinput");
        searchField.setVisible(false);
        Button addButton = (Button) primaryStage.getScene().lookup("#main_addbtn");
        addButton.setVisible(false);
        Button searchButton = (Button) primaryStage.getScene().lookup("#main_searchbtn");
        searchButton.setVisible(false);
    }


    private void showVisibility() {
        TextField searchField = (TextField) primaryStage.getScene().lookup("#main_searchinput");
        searchField.setVisible(true);
        Button addButton = (Button) primaryStage.getScene().lookup("#main_addbtn");
        addButton.setVisible(true);
        Button searchButton = (Button) primaryStage.getScene().lookup("#main_searchbtn");
        searchButton.setVisible(true);
    }
}