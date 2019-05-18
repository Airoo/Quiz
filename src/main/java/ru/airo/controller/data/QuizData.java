package ru.airo.controller.data;

import ru.airo.model.Question;
import ru.airo.model.Result;

import java.util.List;

public class QuizData {
    private static boolean processing = true;
    private static boolean errorAlert = true;
    private static boolean statistic = true;
    private static boolean timing = true;
    private static int questionsCount = Integer.MAX_VALUE;
    private static List<Question> questions;
    private static Result result;

    public static boolean isProcessing() {
        return processing;
    }

    public static void setProcessing(boolean processing) {
        QuizData.processing = processing;
    }

    public static boolean isErrorAlert() {
        return errorAlert;
    }

    public static void setErrorAlert(boolean errorAlert) {
        QuizData.errorAlert = errorAlert;
    }

    public static boolean isStatistic() {
        return statistic;
    }

    public static void setStatistic(boolean statistic) {
        QuizData.statistic = statistic;
    }

    public static boolean isTiming() {
        return timing;
    }

    public static void setTiming(boolean timing) {
        QuizData.timing = timing;
    }

    public static int getQuestionsCount() {
        return questionsCount;
    }

    public static void setQuestionsCount(int questionsCount) {
        QuizData.questionsCount = questionsCount;
    }

    public static List<Question> getQuestions() {
        return questions;
    }

    public static void setQuestions(List<Question> questions) {
        QuizData.questions = questions;
    }

    public static Result getResult() {
        return result;
    }

    public static void setResult(Result result) {
        QuizData.result = result;
    }
}
