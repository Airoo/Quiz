package ru.airo.controller.tabs;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Settings {
    private static final String ALL = "all";
    private static final Logger LOGGER = LoggerFactory.getLogger(Settings.class);

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

    public static Settings getController() {
        FXMLLoader loader = new FXMLLoader(Settings.class.getResource("/views/tabs/Settings.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            LOGGER.error("Ошибка ", e.getMessage());
        }
        return loader.getController();
    }

    public QuizSettings getSettings() {
        QuizSettings settings = new QuizSettings();
        settings.setProcessing(processing.isSelected());
        settings.setErrorAlert(errorAlert.isSelected());
        settings.setStatistic(statistic.isSelected());
        settings.setTiming(timing.isSelected());
        String count = questionsCount.getValue().toString();
        settings.setQuestionsCount(ALL.equals(count) ? Integer.MAX_VALUE : Integer.valueOf(count));
        return settings;
    }

    @Getter
    @Setter
    class QuizSettings {
        boolean processing;
        boolean errorAlert;
        boolean statistic;
        boolean timing;
        int questionsCount;
    }
}
