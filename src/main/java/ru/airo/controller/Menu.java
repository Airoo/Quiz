package ru.airo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class Menu {
    @FXML
    private MenuItem restart;

    private Main mainApp;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void restartGame() {
        mainApp.restartQuiz();
    }

    @FXML
    private void loadNewGame() {
        mainApp.loadQuiz();
    }

    @FXML
    private void quit() {
        System.exit(0);
    }

    public void enableRestartMenuItem() {
        restart.setVisible(true);
        restart.setDisable(false);
    }
}
