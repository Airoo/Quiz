package ru.sbt.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import ru.sbt.model.AnswerVariant;
import ru.sbt.model.QuizeQuastion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Контроллер для "QuizView"
 */
public class QuizViewController {
    private Controller mainApp;
    private QuizeQuastion quizeQuastion;
    @FXML
    private Label question;
    @FXML
    private Label answersCount;
    @FXML
    private Label check;
    @FXML
    private RadioButton rbA;
    @FXML
    private RadioButton rbB;
    @FXML
    private RadioButton rbC;
    @FXML
    private RadioButton rbD;
    @FXML
    private RadioButton rbE;
    @FXML
    private RadioButton rbF;
    @FXML
    private RadioButton rbG;
    @FXML
    private ToggleGroup rbGroup;
    private RadioButton[] rB;
    @FXML
    private ProgressBar pB;
    @FXML
    private Button bNext;
    @FXML
    private Button bHelp;
    @FXML
    private Button bCheck;

    public QuizViewController() {
        question = new Label("");
        answersCount = new Label("");
        check = new Label("");
        rbGroup = new ToggleGroup();
        pB = new ProgressBar(0);
    }

    @FXML
    private void initialize() {
        rB = new RadioButton[7];
        rB[0] = rbA;
        rB[1] = rbB;
        rB[2] = rbC;
        rB[3] = rbD;
        rB[4] = rbE;
        rB[5] = rbF;
        rB[6] = rbG;
    }

//    private void aloneRadioButton() {
//        rbA.setToggleGroup(rbGroup);
//        rbB.setToggleGroup(rbGroup);
//        rbC.setToggleGroup(rbGroup);
//        rbD.setToggleGroup(rbGroup);
//        rbE.setToggleGroup(rbGroup);
//        rbF.setToggleGroup(rbGroup);
//        rbG.setToggleGroup(rbGroup);
//    }
//
//    private void severalRadioButtons() {
//        rbGroup.getToggles().remove(rbA);
//        rbGroup.getToggles().remove(rbB);
//        rbGroup.getToggles().remove(rbC);
//        rbGroup.getToggles().remove(rbD);
//        rbGroup.getToggles().remove(rbE);
//        rbGroup.getToggles().remove(rbF);
//        rbGroup.getToggles().remove(rbG);
//    }

    public void setMainApp(Controller mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Листнер
     */
    @FXML
    private void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getText()) {
            case "1":
                rb(rbA);
                break;
            case "2":
                rb(rbB);
                break;
            case "3":
                rb(rbC);
                break;
            case "4":
                rb(rbD);
                break;
            case "5":
                rb(rbE);
                break;
            case "6":
                rb(rbF);
                break;
            case "7":
                rb(rbG);
                break;
            default:
                switch (keyEvent.getCode().toString()) {
                    case "ENTER":
                        getNextQuiz();
                        break;
                    case "RIGHT_ARROW":
                        getNextQuiz();
                        break;
                    default:
                        break;
                }
        }
    }

    /**
     * Метод для отображения нажатой кнопки
     */
    private void rb(RadioButton pressed) {
        if (pressed.isVisible()) {
            pressed.setSelected(true);
            bNext.setDisable(false);
        }
    }

    @FXML
    private void rbClicked() {
        bNext.setDisable(false);
    }

    @FXML
    private void bNext() {
        getNextQuiz();
    }

    @FXML
    private void bHelp() {
        check.setText(quizeQuastion.getHelp());
        List<AnswerVariant> answerVariants = quizeQuastion.getAnswerVariants();
        for (int i = 0; i < rB.length; i++) {
            rB[i].setSelected(false);
            for (AnswerVariant answerVariant : answerVariants) {
                if (rB[i].getText().equals(answerVariant.getQuastion()) && answerVariant.isAnswer()) {
                    rB[i].setSelected(true);
                }
            }
        }
        bNext.setDisable(false);
    }

    @FXML
    private void bCheck() {
        List<AnswerVariant> answerVariants = quizeQuastion.getAnswerVariants();
        int countAnswers = 0;
        List<AnswerVariant> answers = new ArrayList<>();
        for (AnswerVariant answerVariant : answerVariants) {
            if (answerVariant.isAnswer()) {
                answers.add(answerVariant);
                countAnswers++;
            }
        }
        List<RadioButton> selectedRadioButtons = new ArrayList<>();
        for (int i = 0; i < rB.length; i++) {
            if (rB[i].isSelected()) {
                selectedRadioButtons.add(rB[i]);
            }
        }

        if (answers.size() == selectedRadioButtons.size()) {
            for (RadioButton radioButton : selectedRadioButtons) {
                for (AnswerVariant answerVariant : answers) {
                    if (radioButton.getText().equals(answerVariant.getQuastion())) {
                        countAnswers--;
                    }
                }
            }
        } else if (answers.size() < selectedRadioButtons.size()) {
            countAnswers = -100;
        } else {
            countAnswers = 100;
        }
        if (countAnswers == 0) {
            check.setText("Верно");
        } else if (countAnswers < 0) {
            check.setText("Неверно, есть лишнее");
        } else {
            check.setText("Неврно, либо не все ответы выбраны");
        }
    }

    //TODO проверку на переходе
    private void getNextQuiz() {
        //String picked = ((Labeled) rbGroup.getSelectedToggle()).getText();
        String picked = "";
        mainApp.stepQuiz(picked);
    }

    /**
     * Метод для наполнения страницы
     */
    public void showQuiz(QuizeQuastion quizeQuastion) {
        this.quizeQuastion = quizeQuastion;
        question.setText(quizeQuastion.getQuastionDescription());
        question.setVisible(true);
        bNext.setDisable(true);
        Collections.shuffle(quizeQuastion.getAnswerVariants());
        List<AnswerVariant> answerVariants = quizeQuastion.getAnswerVariants();
        System.out.println(quizeQuastion);
        int count = 0;
        for (AnswerVariant answerVariant : answerVariants) {
            if (answerVariant.isAnswer()) {
                count++;
            }
        }
        if (count == 1) {
            answersCount.setText("Один ответ");
            //  aloneRadioButton();
        } else if (count > 1) {
            answersCount.setText("Несколько ответов");
            // severalRadioButtons();
        } else {
            answersCount.setText("Нет ответов");
            //aloneRadioButton();
        }
        System.out.println(">>>>>>>>>>>>>>" + count);
        check.setText("");
        for (int i = 0; i < 7; i++) {
            if (i >= quizeQuastion.getAnswerVariants().size()) {
                rB[i].setText("");
                rB[i].setSelected(false);
                rB[i].setVisible(false);
            } else {
                rB[i].setText(quizeQuastion.getAnswerVariants().get(i).getQuastion());
                rB[i].setSelected(false);
                rB[i].setVisible(true);
            }
        }
    }

    /**
     * Обновление progress bar
     */
    public void setProgress(double d) {
        pB.setProgress(d);
    }
}
