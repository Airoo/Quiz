package ru.airo.controller.tabs;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import ru.airo.model.Answer;
import ru.airo.model.Question;
import ru.airo.model.QuizSettings;
import ru.airo.service.Loader;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class Test {
    private Loader dataLoader = new Loader();
    private List<Question> questions;
    private int number = 0;

    @FXML
    private TextArea textArea;
    @FXML
    private Label score;
    @FXML
    private Label time;
    @FXML
    private VBox container;

    public void finishTest(ActionEvent actionEvent) {
    }

    public void startTest(ActionEvent actionEvent) {
        initNewGame();
    }

    public void backBtn(ActionEvent actionEvent) {
        stepQuestion(false);
    }

    public void openBtn(ActionEvent actionEvent) {

    }

    public void nextBtn(ActionEvent actionEvent) {
        stepQuestion(true);
    }

    private void initNewGame() {
        if (Integer.MAX_VALUE == QuizSettings.getQuestionsCount()) {
            questions = dataLoader.load();
        } else {
            questions = dataLoader.load().subList(0, QuizSettings.getQuestionsCount() + 1);
        }
        number = 0;
        textArea.setDisable(false);
        textArea.setText(questions.get(number).getDescription());
        score.setText(number + "/" + (questions.size() - 1));
        score.setVisible(QuizSettings.isStatistic());
        time.setVisible(QuizSettings.isTiming());
        showTime();
    }

    private void stepQuestion(boolean next) {
        int temp = number;
        Question question = new Question();
        if (next && ++temp < questions.size()) {
            question = questions.get(++number);
        } else if (!next && --temp >= 0) {
            question = questions.get(--number);
        }
        textArea.setText(question.getDescription());
        fillContainer(question);
        score.setText(number + "/" + (questions.size() - 1));
    }

    private void fillContainer(Question question) {
        container.getChildren().clear();
        List<Answer> answers = question.getAnswers();
        for (Answer answer : answers) {
            RadioButton radioButton = new RadioButton();
            radioButton.setText(answer.getAnswer());
            container.getChildren().add(radioButton);
        }
    }

    private void showTime() {
        long endTime = 2000000000000L;
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        final Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.millis(500),
                        event -> {
                            final long diff = endTime - System.currentTimeMillis();
                            if (diff < 0) {
                                //  timeLabel.setText( "00:00:00" );
                                time.setText(timeFormat.format(0));
                            } else {
                                time.setText(timeFormat.format(diff));
                            }
                        }
                )
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
