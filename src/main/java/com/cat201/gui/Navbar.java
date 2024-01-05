package com.cat201.gui;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class Navbar {

    VBox root;

    public Navbar(VBox root) {
        this.root = root;
        load();
    }

    public static void createNavbar(VBox root) {
        new Navbar(root);
    }

    private void load() {
        String[] buttons = {"Home", "Clients", "Staff", "Cases", "Documents"};

        for (String button : buttons) {
            Button btn = new Button(button);
            btn.getStyleClass().add("sidebar-button");

            // Add the button to the AnchorPane with id sidebar
            AnchorPane sidebar = (AnchorPane) root.lookup("#sidebar");
            if (sidebar != null) {
                sidebar.getChildren().add(btn);
            } else {
                System.out.println("AnchorPane with id 'sidebar' not found!");
            }
        }
    }
}
