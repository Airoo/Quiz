package ru.airo.controller.data;

import lombok.Getter;
import lombok.Setter;
import ru.airo.model.Question;
import ru.airo.model.Result;

import java.util.List;

public class QuizData {
    @Getter
    @Setter
    private static boolean processing = true;
    @Getter
    @Setter
    private static boolean errorAlert = true;
    @Getter
    @Setter
    private static boolean statistic = true;
    @Getter
    @Setter
    private static boolean timing = true;
    @Getter
    @Setter
    private static int questionsCount = Integer.MAX_VALUE;
    @Getter
    @Setter
    private static List<Question> questions;
    @Getter
    @Setter
    private static Result result;
}
