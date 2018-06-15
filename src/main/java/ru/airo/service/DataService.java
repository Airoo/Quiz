package ru.airo.service;

import ru.airo.controller.data.QuizData;
import ru.airo.model.Question;
import ru.airo.model.Result;

import java.util.List;

public class DataService {
    public void changeQuestionsCount(int questionsCount) {
        QuizData.setQuestionsCount(questionsCount);
    }

    public void changeProcessing(boolean processing) {
        QuizData.setProcessing(processing);
    }

    public void changeErrorAlert(boolean errorAlert) {
        QuizData.setErrorAlert(errorAlert);
    }

    public void changeStatistic(boolean statistic) {
        QuizData.setStatistic(statistic);
    }

    public void changeTiming(boolean timing) {
        QuizData.setTiming(timing);
    }

    public void setQuestions(List<Question> questions) {
        QuizData.setQuestions(questions);
    }

    public List<Question> getAllQuestions() {
        return QuizData.getQuestions();
    }

    public void setResult(Result result) {
        QuizData.setResult(result);
    }

    public Result getResult() {
        return QuizData.getResult();
    }

}
