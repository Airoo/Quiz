package ru.airo.model;

import lombok.Getter;
import lombok.Setter;

public class Result {
    @Getter
    @Setter
    private String time;
    @Getter
    @Setter
    private Integer questionsCount;
    @Getter
    @Setter
    private Integer correctAnswersCount;
    @Getter
    @Setter
    private Integer inCorrectAnswersCount;
}
