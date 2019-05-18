package ru.airo.model;


import java.util.ArrayList;
import java.util.List;

public class Question {
    private String section;
    private String number;
    private String description;
    private String help;
    private AnswerType type;
    private List<Answer> answers = new ArrayList<>();

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public AnswerType getType() {
        return type;
    }

    public void setType(AnswerType type) {
        this.type = type;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

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
