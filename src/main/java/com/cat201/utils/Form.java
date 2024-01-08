package com.cat201.utils;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Form {

    private static void hideVisibility(Stage primaryStage) {
        TextField searchField = (TextField) primaryStage.getScene().lookup("#main_searchinput");
        searchField.setVisible(false);
        Button addButton = (Button) primaryStage.getScene().lookup("#main_addbtn");
        addButton.setVisible(false);
        Button searchButton = (Button) primaryStage.getScene().lookup("#main_searchbtn");
        searchButton.setVisible(false);
    }
    
    public static <T> void changeToAddForm(Text title, Pane mainContent, Stage primaryStage, ArrayList<String> colNames, ArrayList<String> cellData) {
        title.setText("Add Staff");
        Form.hideVisibility(primaryStage);

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

    public static <T> void changeToDetailForm(T data, Text title, Pane mainContent, Stage primaryStage, ArrayList<String> colNames, ArrayList<String> cellData) {
        title.setText("Staff Details");
        Form.hideVisibility(primaryStage);

        Pane detailForm = new Pane();

        // clear the main anchor pane
        mainContent.getChildren().clear();
    
        for(int i=0; i<colNames.size(); i++) {
            TextField textField = new TextField();
            textField.setPromptText(colNames.get(i));
            textField.setText(cellData.get(i));
            textField.setLayoutX(100);
            textField.setLayoutY(100 + i*50);
            textField.setEditable(true);
            detailForm.getChildren().add(textField);
        }

        // add the values to the textfields
        for(int i=0; i<colNames.size(); i++) {
            TextField textField = (TextField) detailForm.getChildren().get(i);
            textField.setText(cellData.get(i));
        }

        mainContent.getChildren().add(detailForm);
    }

}
