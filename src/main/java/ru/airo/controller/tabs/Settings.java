package ru.airo.controller.tabs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import ru.airo.model.QuizSettings;

public class Settings {
    private static final String ALL = "all";

    @FXML
    private CheckBox processing;
    @FXML
    private CheckBox errorAlert;
    @FXML
    private CheckBox statistic;
    @FXML
    private CheckBox timing;
    @FXML
    private ComboBox questionsCount;

    public void changeQuestionsCount(ActionEvent actionEvent) {
        String count = questionsCount.getValue().toString();
        QuizSettings.setQuestionsCount(ALL.equals(count) ? Integer.MAX_VALUE : Integer.valueOf(count));
    }

    public void changeProcessing(ActionEvent actionEvent) {
        QuizSettings.setProcessing(processing.isSelected());
    }

    public void changeErrorAlert(ActionEvent actionEvent) {
        QuizSettings.setErrorAlert(errorAlert.isSelected());
    }

    public void changeStatistic(ActionEvent actionEvent) {
        QuizSettings.setStatistic(statistic.isSelected());
    }

    public void changeTiming(ActionEvent actionEvent) {
        QuizSettings.setTiming(timing.isSelected());
    }
}
