package ru.airo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuizSettings {
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
}
