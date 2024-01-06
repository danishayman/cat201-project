package com.cat201.controller;

import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;

public class NavController {

    private Stage primaryStage;
    private Connection conn;
    private Text title;
    
    public NavController(Stage primaryStage, Connection conn) {
        this.primaryStage = primaryStage;
        this.conn = conn;
        title = (Text) primaryStage.getScene().lookup("#main_title");
    }

    public void createEventListeners() {
        Button navHome = (Button) primaryStage.getScene().lookup("#nav_home");
        Button navClient = (Button) primaryStage.getScene().lookup("#nav_clients");
        Button navStaff = (Button) primaryStage.getScene().lookup("#nav_staffs");
        Button navCase = (Button) primaryStage.getScene().lookup("#nav_cases");


        navStaff.setOnAction(e -> {
            title.setText("Staff");
            StaffController staffController = new StaffController(conn);
            staffController.load();
        });

    }
}
