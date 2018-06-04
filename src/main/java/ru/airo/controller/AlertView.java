package ru.airo.controller;

import javafx.scene.control.Alert.AlertType;

public class AlertView {
    public AlertView(String msg) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Warning");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
