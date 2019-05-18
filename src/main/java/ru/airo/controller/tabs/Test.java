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
import ru.airo.controller.AlertView;
import ru.airo.controller.data.QuizData;
import ru.airo.model.Answer;
import ru.airo.model.Question;
import ru.airo.model.Result;
import ru.airo.service.DataService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Test {
    private static final int MAX_LENGTH = 120;
    private static final int DOUBLE_ROW = 40;
    private static final int ONE_ROW = 25;
    private static final String TIME_FORMAT = "HH:mm:ss";

    private DataService dataService = new DataService();
    private List<Question> questions;
    private int currentStep = 0;
    private Timeline timeline;
    private Map<Integer, List<Answer>> savedAnswers;

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
        collectResult();
    }

    private void collectResult() {
        Result result = new Result();
        result.setQuestionsCount(questions.size());
        result.setTime(time.getText());
        int correctAnswers = 0;
//        for (int i = 0; i < questions.size(); i++) {
//            List<Answer> originalAnswers = questions.get(i).getAnswers();
//            if (savedAnswers.containsKey(i)) {
//                for (int j = 0; j < savedAnswers.get(i).size(); j++) {
//                    if (originalAnswers.get(j).isCorrect() != savedAnswers.get(i).get(j).isCorrect()) {
//                        break;
//                    }
//                }
//                correctAnswers++;
//            }
//        }
//        System.out.println(correctAnswers);
        dataService.setResult(result);
    }

    public void startTest(ActionEvent actionEvent) {
        savedAnswers = new HashMap<>();
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
        saveAnswers();
        changeStepQuestion(false);
        fillContainer(questions.get(currentStep));
    }

    public void openBtn(ActionEvent actionEvent) {
        //TODO Костыль для вывода правильных ответов в консоль
        List<String> correctAnswers = questions.get(currentStep).getAnswers().stream()
                .filter(Answer::isCorrect)
                .map(Answer::getAnswer)
                .collect(Collectors.toList());
        StringBuilder result = new StringBuilder();
        for (String correctAnswer : correctAnswers) {
            result.append(correctAnswer).append("\n");
        }
        new AlertView(result.toString());
    }

    public void nextBtn(ActionEvent actionEvent) {
        saveAnswers();
        changeStepQuestion(true);
        fillContainer(questions.get(currentStep));
    }

    private void initNewGame() {
        if (Integer.MAX_VALUE == QuizData.getQuestionsCount()) {
            questions = dataService.getAllQuestions();
        } else {
            questions = dataService.getAllQuestions().subList(0, QuizData.getQuestionsCount() + 1);
        }
        currentStep = 0;
        textArea.setDisable(false);
        textArea.setText(questions.get(currentStep).getDescription());
        score.setText(currentStep + "/" + (questions.size() - 1));
        score.setVisible(QuizData.isProcessing());
        time.setVisible(QuizData.isTiming());
        showTime();
        fillContainer(questions.get(currentStep));
    }

    private void changeStepQuestion(boolean next) {
        int temp = currentStep;
        if (next && ++temp < questions.size()) {
            textArea.setText(questions.get(++currentStep).getDescription());
        } else if (!next && --temp >= 0) {
            textArea.setText(questions.get(--currentStep).getDescription());
        }
        score.setText(currentStep + "/" + (questions.size() - 1));
    }

    private void fillContainer(Question question) {
        container.getChildren().clear();
        List<Answer> answers = question.getAnswers();
        boolean saved = false;
        if (savedAnswers.containsKey(currentStep)) {
            answers = savedAnswers.get(currentStep);
            saved = true;
        }
        for (Answer answer : answers) {
            RadioButton radioButton = new RadioButton();
            if (answer.getAnswer().length() > MAX_LENGTH) {
                radioButton.setMinHeight(DOUBLE_ROW);
            } else {
                radioButton.setMinHeight(ONE_ROW);
            }
            radioButton.setText(answer.getAnswer());
            if (saved) {
                radioButton.setSelected(answer.isCorrect());
            }
            radioButton.wrapTextProperty().setValue(true);
            container.getChildren().add(radioButton);
        }
    }

    private void showTime() {
        long startTime = System.currentTimeMillis();
        DateFormat timeFormat = new SimpleDateFormat(TIME_FORMAT);
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

    private void saveAnswers() {
        ObservableList<Node> radioButtons = container.getChildren();
        ArrayList<Answer> newAnswers = new ArrayList<>();
        for (Node radioButton : radioButtons) {
            Answer dataAnswer = new Answer();
            dataAnswer.setAnswer(((RadioButton) radioButton).getText());
            dataAnswer.setCorrect(((RadioButton) radioButton).isSelected());
            newAnswers.add(dataAnswer);
        }
        savedAnswers.put(currentStep, newAnswers);
    }
}
