package ru.airo.controller.tabs;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
import java.util.*;

public class Test {
    private static final int MAX_LENGHT = 120;
    private static final int DOUBLE_ROW = 40;
    private static final int ONE_ROW = 25;

    private Loader dataLoader = new Loader();
    private List<Question> questions;
    private int number = 0;
    private Timeline timeline;
    private Map<Integer, List<Answer>> dataAnswers;

    @FXML
    private TextArea textArea;
    @FXML
    private Label score;
    @FXML
    private Label time;
    @FXML
    private Button start;
    @FXML
    private Button finish;
    @FXML
    private Button next;
    @FXML
    private Button prev;
    @FXML
    private VBox container;

    public void finishTest(ActionEvent actionEvent) {
        initButtons(false);
        timeline.stop();
        container.getChildren().clear();
        score.setVisible(false);
        time.setVisible(false);
    }

    public void startTest(ActionEvent actionEvent) {
        dataAnswers = new HashMap<>();
        initButtons(true);
        initNewGame();
    }

    private void initButtons(boolean init) {
        start.setDisable(init);
        finish.setDisable(!init);
        next.setDisable(!init);
        prev.setDisable(!init);
        textArea.clear();
        textArea.setDisable(!init);
    }

    public void backBtn(ActionEvent actionEvent) {
        setAnswersToData();
        stepQuestion(false);
        fillContainer(questions.get(number));
    }

    public void nextBtn(ActionEvent actionEvent) {
        setAnswersToData();
        stepQuestion(true);
        fillContainer(questions.get(number));
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
        score.setVisible(QuizSettings.isProcessing());
        time.setVisible(QuizSettings.isTiming());
        showTime();
        fillContainer(questions.get(number));
    }

    private void stepQuestion(boolean next) {
        int temp = number;
        if (next && ++temp < questions.size()) {
            textArea.setText(questions.get(++number).getDescription());
        } else if (!next && --temp >= 0) {
            textArea.setText(questions.get(--number).getDescription());
        }
        score.setText(number + "/" + (questions.size() - 1));
    }

    private void fillContainer(Question question) {
        container.getChildren().clear();
        List<Answer> answers = question.getAnswers();
        for (Answer answer : answers) {
            RadioButton radioButton = new RadioButton();
            if (answer.getAnswer().length() > MAX_LENGHT) {
                radioButton.setMinHeight(DOUBLE_ROW);
            } else {
                radioButton.setMinHeight(ONE_ROW);
            }
            radioButton.setText(answer.getAnswer());
            radioButton.wrapTextProperty().setValue(true);
            container.getChildren().add(radioButton);
        }
    }

    private void showTime() {
        long startTime = System.currentTimeMillis();
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        timeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        timeline = new Timeline(
                new KeyFrame(
                        Duration.millis(500),
                        event -> time.setText(timeFormat.format(System.currentTimeMillis() - startTime))
                )
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void setAnswersToData() {
        ObservableList<Node> answers = container.getChildren();
        if (answers.isEmpty()) {
            dataAnswers.put(number, Collections.emptyList());
            return;
        }
        ArrayList<Answer> newAnswers = new ArrayList<>();
        for (Node answer : answers) {
            Answer dataAnswer = new Answer();
            dataAnswer.setAnswer(((RadioButton) answer).getText());
            dataAnswer.setCorrect(((RadioButton) answer).isSelected());
            newAnswers.add(dataAnswer);
        }
        dataAnswers.put(number, newAnswers);
    }
}
