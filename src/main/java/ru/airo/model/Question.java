package ru.airo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Question {
    private String section;
    private String number;
    private String description;
    private String help;
    private AnswerType type;
    private List<Answer> answers = new ArrayList<>();

    @Override
    public String toString() {
        return "Question{" +
                "section='" + section + '\'' +
                ", number='" + number + '\'' +
                ", description='" + description + '\'' +
                ", help='" + help + '\'' +
                ", type=" + type +
                ", answers=" + answers +
                '}';
    }
}
