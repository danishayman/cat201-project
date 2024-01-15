package com.cat201.utils;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.cat201.beans.Case;
import com.cat201.dao.ClientDAO;
import com.cat201.dao.StaffDAO;

public class CaseTable {

    public static void generateTable(List<Case> data, TableView<Case> tableView, Pane mainContent, Consumer<Case> callback, Connection conn) {

        if(data.size() == 0 || data == null) {
            mainContent.getChildren().clear();
            mainContent.getChildren().add(new Button("No data found"));
            return;
        }

        ArrayList<String> colNames = new Case().getColumnNames();
        ArrayList<String> cellData = new Case().getCellData();
        for(int i=0; i<colNames.size(); i++) {
            TableColumn<Case, String> column = new TableColumn<>(colNames.get(i));
            if(i >= 6) {
                column.setCellValueFactory(new PropertyValueFactory<>("staffName"));
            }else {
                column.setCellValueFactory(new PropertyValueFactory<>(cellData.get(i)));
            }
            tableView.getColumns().add(column);
        }

        for(Case c: data) {
            String clientName = new ClientDAO(conn).getClient(c.getID()).getFirstName();
        }





        // // add details button the last column
        // TableColumn<Case, Void> detailColumn = new TableColumn<>("Details");
        // detailColumn.setCellFactory(param -> new TableCell<Case, Void>() {
        //     private final Button detailButton = new Button("Details");
        //     {
        //         detailButton.setOnAction(event -> {
        //             Case data = getTableView().getItems().get(getIndex());
        //             callback.accept(data);
        //         });
        //     }

        //     @Override
        //     public void updateItem(Void item, boolean empty) {
        //         super.updateItem(item, empty);
        //         setGraphic(empty ? null : detailButton);
        //     }
        // });
        // tableView.getColumns().add(detailColumn);


        // Enable horizontal scrolling by setting column resize policy
        tableView.setMaxHeight(Double.MAX_VALUE);
        tableView.setMaxWidth(Double.MAX_VALUE);
        tableView.prefWidthProperty().bind(mainContent.widthProperty());

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.getStyleClass().add("table-view");

        mainContent.getChildren().clear();
        mainContent.getChildren().add(tableView);
    }
}
