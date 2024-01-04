package com.cat201;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.sql.*;

import com.cat201.dao.StaffDAO;
import com.cat201.utils.Toast;
import com.cat201.beans.Staff;


public class Main extends Application {

    static Connection conn;

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));
        	VBox root = loader.load();
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			
			// //COPY CODE FROM LoginWindow
		    // GridPane gridpane = new GridPane();
		    
		    // //Setting up the look and feel
		    // gridpane.setPadding(new Insets(5));
		    // gridpane.setHgap(5);
		    // gridpane.setVgap(5);
		    
		    // ColumnConstraints column1 = new ColumnConstraints(100);
		    // ColumnConstraints column2 = new ColumnConstraints(50, 150, 300);
		    // column2.setHgrow(Priority.ALWAYS);
		    // gridpane.getColumnConstraints().addAll(column1, column2);

		    // //Initialize the components
		    // Label fNameLbl = new Label("First Name");
		    // TextField fNameFld = new TextField();
		    // Label lNameLbl = new Label("Last Name");
		    // TextField lNameFld = new TextField();
		    // Button signButt = new Button("Sign in");
		    // fNameLbl.setId("user-id");

		    // // First name label, set to 0,0
		    // GridPane.setHalignment(fNameLbl, HPos.RIGHT);
		    // gridpane.add(fNameLbl, 0, 0);

		    // // Last name label, set to 0,1
		    // GridPane.setHalignment(lNameLbl, HPos.RIGHT);
		    // gridpane.add(lNameLbl, 0, 1);

		    // // First name field, set to 1,0
		    // GridPane.setHalignment(fNameFld, HPos.LEFT);
		    // gridpane.add(fNameFld, 1, 0);

		    // // Last name field, set to 1,1
		    // GridPane.setHalignment(lNameFld, HPos.LEFT);
		    // gridpane.add(lNameFld, 1, 1);

		    // // Save button, set to 1,2
		    // GridPane.setHalignment(signButt, HPos.RIGHT);
		    // gridpane.add(signButt, 1, 2);

			// create button to add staff member
			Button addStaffButton = new Button("Add Staff");
			addStaffButton.setOnAction(e -> {
				StaffDAO staffDAO = new StaffDAO(conn);
				boolean response = staffDAO.addStaff(new Staff(1, "John", "Doe", "12345678", "123 Fake Street", "123456", "Company", "hehehe", "password"));
				if(response) {
					Toast.get().showSuccess("Staff added successfully");
				} else {
					Toast.get().showError("Staff not added");
				}
			});

			// add addStaffButton to root
			root.getChildren().add(addStaffButton);

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
