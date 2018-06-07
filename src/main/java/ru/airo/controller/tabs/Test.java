package ru.airo.controller.tabs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.airo.model.Question;
import ru.airo.service.Loader;

import java.io.IOException;
import java.util.List;

public class Test {
    private static final Logger LOGGER = LoggerFactory.getLogger(Test.class);

    private Loader dataLoader = new Loader();
    private Settings settings = Settings.getController();
    private List<Question> questions;

    @FXML
    private TextArea textArea;

    public void finishTest(ActionEvent actionEvent) {
    }

    public void startTest(ActionEvent actionEvent) throws IOException {
        Settings.QuizSettings quizSettings = settings.getSettings();
        if (Integer.MAX_VALUE == quizSettings.getQuestionsCount()) {
            questions = dataLoader.load();
        } else {
            questions = dataLoader.load().subList(0, quizSettings.getQuestionsCount());
        }
        textArea.setDisable(false);
        textArea.setText(questions.get(0).getDescription());
    }

    public void backBtn(ActionEvent actionEvent) {

    }

    public void openBtn(ActionEvent actionEvent) {

    }

    public void nextBtn(ActionEvent actionEvent) {

    }
}
