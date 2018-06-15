package ru.airo.controller.tabs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import ru.airo.service.DataService;

public class Settings {
    private static final String ALL = "all";
    private DataService dataService = new DataService();

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
        dataService.changeQuestionsCount(ALL.equals(count) ? Integer.MAX_VALUE : Integer.valueOf(count));
    }

    public void changeProcessing(ActionEvent actionEvent) {
        dataService.changeProcessing(processing.isSelected());
    }

    public void changeErrorAlert(ActionEvent actionEvent) {
        dataService.changeErrorAlert(errorAlert.isSelected());
    }

    public void changeStatistic(ActionEvent actionEvent) {
        dataService.changeStatistic(statistic.isSelected());
    }

    public void changeTiming(ActionEvent actionEvent) {
        dataService.changeTiming(timing.isSelected());
    }
}
