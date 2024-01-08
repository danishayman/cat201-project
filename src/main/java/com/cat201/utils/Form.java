package com.cat201.utils;

import java.util.ArrayList;
import java.util.function.Consumer;

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
            textField.setLayoutY(100 + i*50);
            addStaffForm.getChildren().add(textField);
        }

        mainContent.getChildren().add(addStaffForm);
    }

    public static <T> void changeToDetailForm(T data, Text title, Pane mainContent, Stage primaryStage, ArrayList<String> colNames, Consumer<ArrayList<String>> callback) {
        title.setText("Staff Details");
        Form.hideVisibility(primaryStage);

        Pane detailForm = new Pane();
    
        for(int i=0; i<colNames.size(); i++) {
            TextField textField = new TextField();
            textField.setPromptText(colNames.get(i));
            textField.setMaxWidth(Double.MAX_VALUE);
            textField.prefWidthProperty().bind(detailForm.widthProperty());
            // invoke the getter method from the object
            try {
                String methodName = "get" + String.join("", colNames.get(i).split(" "));
                Object result = data.getClass().getMethod(methodName).invoke(data);
            
                // Convert the result to String before setting it in the textField
                textField.setText(String.valueOf(result));
            } catch (Exception e) {
                e.printStackTrace(); // Handle the exception appropriately
            }

            textField.setLayoutY(100 + i*50);
            if(colNames.get(i) == "ID") {
                textField.setEditable(false);
            }
            detailForm.getChildren().add(textField);
        }

        // add submit button
        Button submitButton = new Button("Submit");
        submitButton.setLayoutY(100 + colNames.size()*50);

        submitButton.setOnAction(e -> {
            ArrayList<String> newdata = new ArrayList<>();
            for(int i=0; i<colNames.size(); i++) {
                TextField textField = (TextField) detailForm.getChildren().get(i);
                if(textField.getText().equals("")) {
                    Toast.get().showError(colNames.get(i) + " cannot be empty");
                    return;
                }
                newdata.add(textField.getText());
            }
            callback.accept(newdata);
        });

        detailForm.getChildren().add(submitButton);
        detailForm.prefWidthProperty().bind(mainContent.widthProperty());

        mainContent.getChildren().clear();
        mainContent.getChildren().add(detailForm);
    }


}
