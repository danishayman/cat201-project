package com.cat201.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import com.cat201.beans.Client;

import com.cat201.dao.ClientDAO;
import com.cat201.utils.Form;
import com.cat201.utils.Table;

public class ClientController {
    
    private Stage primaryStage;
    private ClientDAO clientDAO;
    private TableView<Client> tableView;

    private Pane mainContent;
    private Text title;

    private ArrayList<String> colNames;
    private ArrayList<String> cellData;

    public ClientController(Stage primaryStage, Connection conn, Pane mainContent) {
        this.primaryStage = primaryStage;
        this.mainContent = mainContent;
        clientDAO = new ClientDAO(conn);
        tableView = new TableView<>();
        title = (Text) primaryStage.getScene().lookup("#main_title");

        // Get column names and cell data
        Client clientobj = new Client();
        colNames = clientobj.getColumnNames();
        cellData = clientobj.getCellData();
    }

    public void load() {
        title.setText("Clients List");
        // Fetch data from the database
        List<Client> clients = fetchData(); 

        // Search button functionality
        Button searchButton = (Button) primaryStage.getScene().lookup("#main_searchbtn");
        searchButton.setOnAction(e -> {
            searchData();
        });

        // Add button functionality
        Button addButton = (Button) primaryStage.getScene().lookup("#main_addbtn");
        addButton.setOnAction(e -> {
            changeToAddClient();
        });
        
        // Generate table
        Table.generateTable(clients, tableView, mainContent, colNames, cellData, this::changeToDetailClient);
    }

    private List<Client> fetchData() {
        return clientDAO.getClients();
    }

    private void searchData() {
        TextField searchField = (TextField) primaryStage.getScene().lookup("#main_searchinput");
        String searchInput = searchField.getText();

        // If search input is empty, return
        if(searchInput.equals("")) return;

        String stmt = "SELECT * FROM Clients " +
                     "WHERE firstName LIKE '%" + searchInput + "%' " +
                     "   OR lastName LIKE '%" + searchInput + "%' " +
                     "   OR phone LIKE '%" + searchInput + "%' " +
                     "   OR email LIKE '%" + searchInput + "%'" + 
                     "   OR createdBy LIKE '%" + searchInput + "%'";
        List<Client> clients = clientDAO.getClientQuery(stmt);

        Client clientobj = new Client();
        ArrayList<String> colNames = clientobj.getColumnNames();
        ArrayList<String> cellData = clientobj.getCellData();

        // Generate table
        Table.generateTable(clients, tableView, mainContent, colNames, cellData, this::changeToDetailClient);
    }

    public void changeToAddClient() {
        Form.changeToAddForm(title, mainContent, primaryStage, colNames, cellData);
    }

    public void changeToDetailClient(Client data) {
        Form.changeToDetailForm(data, title, mainContent, primaryStage, colNames, cellData);
    }
}
