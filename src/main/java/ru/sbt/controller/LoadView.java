package ru.sbt.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class LoadView {
    private Main mainApp;
    @FXML
    private Label headline;
    @FXML
    private Label result;
    @FXML
    private Button resume;
    @FXML
    private Button restart;
    @FXML
    private Button load;

    public LoadView() {
        headline = new Label();
        result = new Label();
    }

    @FXML
    private void initialize() {
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void bResume() throws Exception {
        mainApp.resumeQuiz();
    }

    @FXML
    private void bRestart() throws Exception {
        mainApp.restartQuiz();
    }

    @FXML
    private void bLoad() throws Exception {
        mainApp.loadQuiz();
    }

    public void showResult(int score, int max, int attempt) {
        restart.setVisible(true);
        headline.setText("Result from Quiz:");
        if (score == max) {
            result.setText("Congratulations, you scored the full " + score + " points!\n"
                    + "(It took " + attempt + " attempt" + (attempt > 1 ? "s)" : ")"));
            resume.setVisible(false);
        } else if (score == 0) {
            result.setText("Sorry, you didn't have any correct answer.");
            resume.setVisible(true);
        } else {
            result.setText("You scored " + score + " out of " + max + " points.");
            resume.setVisible(true);
        }
    }
}