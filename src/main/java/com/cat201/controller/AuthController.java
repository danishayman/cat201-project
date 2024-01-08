package com.cat201.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;

import com.cat201.beans.Staff;
import com.cat201.dao.AuthDAO;
import com.cat201.manager.UserManager;
import com.cat201.utils.Toast;

public class AuthController {
    
    private Stage primaryStage;
    private LoginCallback loginCallback;
    private AuthDAO auth;

    public AuthController(Stage primaryStage, Connection conn, LoginCallback loginCallback) {
        this.primaryStage = primaryStage;
        this.loginCallback = loginCallback;
        auth = new AuthDAO(conn);
    }

    public interface LoginCallback {
        void onLoginSuccess(Stage primaryStage);
    }

    private void setNewStage(String title, VBox root) {
        primaryStage.setTitle(title);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    // Pages creation for login 
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

 // Pages creation for signup
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

    // event listeners for login page
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
                    runLogin(root);
                    break;
                default:
                    break;
            }
        });
        login.setOnAction(e -> {
            runLogin(root);
        });
    }

    // Login function
    private void runLogin(VBox root) {
        // Get username and password
        String email = ((javafx.scene.control.TextField) root.lookup("#login_email")).getText();
        String password = ((javafx.scene.control.TextField) root.lookup("#login_password")).getText();

        if(email.equals("") || password.equals("")) {
            Toast.get().showError("Email or password is empty");
            return;
        }

        Staff staff = auth.login(email, password);
        if(staff != null) {
            Toast.get().showSuccess("Login successful");

            UserManager.get().setUser("staff", staff);

            if(loginCallback != null) {
                loginCallback.onLoginSuccess(this.primaryStage);
            }
        } else {
            Toast.get().showError("Incorrect email or password");
        }
    }

    // event listeners for signup page
    public void createSignUpEventListeners(VBox root) {
        // Change the scene to login
        Hyperlink loginLink = (Hyperlink) root.lookup("#loginLink");
        loginLink.setOnAction(event -> {
            createLogin();
        });

         // Sign up button event listener
        Button signup = (Button) root.lookup("#signupButton");
        // add enter key event listener
        root.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ENTER:
                    runSignUp(root);
                    break;
                default:
                    break;
            }
        });
        signup.setOnAction(e -> {
            runSignUp(root);
        });
    }

    // Signup function
    private void runSignUp(VBox root) {
        // account details
        String firstname = ((javafx.scene.control.TextField) root.lookup("#signup_firstname")).getText();
        String lastname = ((javafx.scene.control.TextField) root.lookup("#signup_lastname")).getText();
        String phone = ((javafx.scene.control.TextField) root.lookup("#signup_phone")).getText();
        String address = ((javafx.scene.control.TextField) root.lookup("#signup_address")).getText();

        // account credentials
        String email = ((javafx.scene.control.TextField) root.lookup("#signup_email")).getText();
        String password = ((javafx.scene.control.TextField) root.lookup("#signup_password")).getText();

        String[] inputs = {firstname, lastname, phone, address, email, password};
        String errors = "";
        for(String input : inputs) {
            if(input.equals("")) {
                errors += input + " is empty\n";
                return;
            }
        }
        if(!errors.equals("")) {
            Toast.get().showError(errors);
            return;
        }

        boolean response = auth.register(firstname, lastname, phone, address, email, password);
        if(response) {
            Toast.get().showSuccess("Signup successful");
            // proceed to go to login page
            createLogin();
        } else {
            Toast.get().showError("Signup failed");
        }
    }

}
