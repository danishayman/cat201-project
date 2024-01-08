package com.cat201.controller;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;

import com.cat201.utils.Utils;

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
            Utils.get().show();
            ClientController clientController = new ClientController(primaryStage, conn, mainContent);
            clientController.load();
        });

        navStaff.setOnAction(e -> {
            Utils.get().show();
            StaffController staffController = new StaffController(primaryStage, conn, mainContent);
            staffController.load();
        });

        navCase.setOnAction(e -> {
            Utils.get().show();
            CaseController caseController = new CaseController(primaryStage, conn, mainContent);
            caseController.load();
        });
    }

    private void showHome() {
        Text title = (Text) primaryStage.getScene().lookup("#main_title");
        title.setText("Dashboard");
        // Hide search and add button in the homepage
        Utils.get().hide();
    }
}