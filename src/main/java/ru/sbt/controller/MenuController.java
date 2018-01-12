package ru.sbt.controller;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

/**
 * Контроллер для "Menu"
 */
public class MenuController {

    private Controller mainApp;
    @FXML
    private MenuItem mIRestart;

    public void setMainApp(Controller mainApp) {
        this.mainApp = mainApp;
    }

    //Листнеры для кнопок "Menu"
    @FXML
    private void restartGame() throws Exception {
        mainApp.restartQuiz();
    }

    @FXML
    private void loadNewGame() throws Exception {
        mainApp.loadQuiz();
    }

    @FXML
    private void quit() {
        System.exit(0);
    }

    public void enableRestartMenuItem() {
        mIRestart.setVisible(true);
        mIRestart.setDisable(false);
    }
}
