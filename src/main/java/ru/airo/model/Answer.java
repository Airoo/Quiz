package ru.airo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Answer {
    private String answer;
    private boolean isCorrect;

    @Override
    public String toString() {
        return "Answer{" +
                "answer='" + answer + '\'' +
                ", isCorrect=" + isCorrect +
                '}';
    }
}
