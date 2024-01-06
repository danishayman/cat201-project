package com.cat201.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class Table {

    public interface TableData {
        ArrayList<String> getColumnNames();
        ArrayList<String> getCellData();
    }

    public static <T> void generateTable(List<T> data, ScrollPane mainContent, TableView<T> tableView, Stage primaryStage, ArrayList<String> colNames, ArrayList<String> cellData) {
        // Assuming you have a proper model class
        ObservableList<T> observableData = FXCollections.observableArrayList(data);

        // Clear existing columns
        tableView.getColumns().clear();

        for(int i=0; i<cellData.size(); i++) {
            TableColumn<T, String> column = new TableColumn<>(colNames.get(i));
            column.setCellValueFactory(new PropertyValueFactory<>(cellData.get(i)));
            tableView.getColumns().add(column);
        }

        // Add data to the table
        tableView.setItems(observableData);

        // Enable horizontal scrolling by setting column resize policy
        tableView.setMaxHeight(Double.MAX_VALUE);
        tableView.setMaxWidth(Double.MAX_VALUE);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.getStyleClass().add("table-view");

        Pane main = (Pane) primaryStage.getScene().lookup("#main_content");
        main.getChildren().clear();
        main.getChildren().add(tableView);
    }
}
