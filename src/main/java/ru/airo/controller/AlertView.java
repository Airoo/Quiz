package ru.airo.controller;

import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;

public class AlertView {
    public AlertView(String msg) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Warning");
        TextArea textArea = new TextArea();
        textArea.setText(msg);
        alert.getDialogPane().setContent(textArea);
        alert.showAndWait();
    }
}
