package com.cat201.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;

import com.cat201.dao.authDAO;
import com.cat201.utils.Toast;

public class AuthController {
    

    private Stage primaryStage;
    private Connection conn;

    public AuthController(Stage primaryStage, Connection conn) {
        this.primaryStage = primaryStage;
        this.conn = conn;
    }

    private void setNewStage(String title, VBox root) {
        primaryStage.setTitle(title);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void createLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
            VBox root = loader.load();

            createLoginEventListeners(root);
            setNewStage("Login", root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createSignUp() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/signup.fxml"));
            VBox root = loader.load();

            // Create event listeners
            createSignUpEventListeners(root);
            setNewStage("Sign Up", root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createLoginEventListeners(VBox root) {
        Hyperlink signupLink = (Hyperlink) root.lookup("#signupLink");
        signupLink.setOnAction(e -> {
            createSignUp();
        });

        // Login button event listener
        Button login = (Button) root.lookup("#loginButton");
        // add enter key event listener
        root.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ENTER:
                    gotoLogin(root);
                    break;
                default:
                    break;
            }
        });
        login.setOnAction(e -> {
            gotoLogin(root);
        });
    }

    private void gotoLogin(VBox root) {
        // Get username and password
        String username = ((javafx.scene.control.TextField) root.lookup("#login_username")).getText();
        String password = ((javafx.scene.control.TextField) root.lookup("#login_password")).getText();

        if(username.equals("") || password.equals("")) {
            System.out.println("Username or password is empty");
            return;
        }

        // Check if username and password are correct
        authDAO auth = new authDAO(conn);
        boolean response = auth.login(username, password);
        if(response) {
            Toast.get().showSuccess("Login successful");
        } else {
            Toast.get().showError("Incorrect username or password");
        }
    }

    public void createSignUpEventListeners(VBox root) {
        // Change the scene to login
        Hyperlink loginLink = (Hyperlink) root.lookup("#loginLink");
        loginLink.setOnAction(event -> {
            createLogin();
        });
    }
}
