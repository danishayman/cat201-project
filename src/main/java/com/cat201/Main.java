package com.cat201;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import java.sql.*;

import com.cat201.beans.Staff;

import com.cat201.dao.StaffDAO;

import com.cat201.gui.Navbar;
import com.cat201.utils.Toast;

public class Main extends Application {

    static Connection conn;

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));
        	VBox root = loader.load();
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
		
			// gui initialization
			// Navbar.createNavbar(root);

			// create button to add staff member
			// Button addStaffButton = new Button("Add Staff");
			// addStaffButton.setOnAction(e -> {
			// 	StaffDAO staffDAO = new StaffDAO(conn);
			// 	boolean response = staffDAO.addStaff(new Staff(1, "John", "Doe", "12345678", "123 Fake Street", "123456", "Company", "hehehe", "password"));
			// 	if(response) {
			// 		Toast.get().showSuccess("Staff added successfully");
			// 	} else {
			// 		Toast.get().showError("Staff not added");
			// 	}
			// });

			// Button getStaffsButton = new Button("Get Staffs");
			// getStaffsButton.setOnAction(e -> {
			// 	StaffDAO staffDAO = new StaffDAO(conn);
			// 	Staff[] staffs = staffDAO.getStaffs();
			// 	for(Staff staff : staffs) {
			// 		System.out.println(staff.toString());
			// 	}
			// });

			// Button updateStaffButton = new Button("Update Staff");
			// updateStaffButton.setOnAction(e -> {
			// 	StaffDAO staffDAO = new StaffDAO(conn);
			// 	boolean response = staffDAO.updateStaff(new Staff(2, "Eminence", "Doe", "12345678", "123 Fake Street", "123456", "Company", "hehehe", "password"));
			// 	if(response) {
			// 		Toast.get().showSuccess("Staff updated successfully");
			// 	} else {
			// 		Toast.get().showError("Staff not updated");
			// 	}
			// });

			// Button deleteStaffButton = new Button("Delete Staff");
			// deleteStaffButton.setOnAction(e -> {
			// 	StaffDAO staffDAO = new StaffDAO(conn);
			// 	boolean response = staffDAO.deleteStaff(1);
			// 	if(response) {
			// 		Toast.get().showSuccess("Staff deleted successfully");
			// 	} else {
			// 		Toast.get().showError("Staff not deleted");
			// 	}
			// });

			// // add addStaffButton to root
			// root.getChildren().add(getStaffsButton);
			// root.getChildren().add(addStaffButton);
			// root.getChildren().add(updateStaffButton);
			// root.getChildren().add(deleteStaffButton);


			primaryStage.setTitle("Law Firm Management System");
		    primaryStage.setScene(scene);
		    primaryStage.show();
		    
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception{
        String url = "jdbc:sqlite:lawfirm.db";
        conn = DriverManager.getConnection(url);
        System.out.println("Connection to SQLite has been established.");
		launch(args);
	}
}
