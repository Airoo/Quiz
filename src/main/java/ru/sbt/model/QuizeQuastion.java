package ru.sbt.model;

import java.util.ArrayList;
import java.util.List;

public class QuizeQuastion {
    private String section;
    private String number;
    private String quastionDescription;
    private String help;
    private AnswersTypes answersTypes;
    private List<AnswerVariant> answerVariants = new ArrayList<>();

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

    public String getQuastionDescription() {
        return quastionDescription;
    }

    public void setQuastionDescription(String quastionDescription) {
        this.quastionDescription = quastionDescription;
    }

    public AnswersTypes getAnswersTypes() {
        return answersTypes;
    }

    public void setAnswersTypes(AnswersTypes answersTypes) {
        this.answersTypes = answersTypes;
    }

    public List<AnswerVariant> getAnswerVariants() {
        return answerVariants;
    }

    public void setAnswerVariants(List<AnswerVariant> answerVariants) {
        this.answerVariants = answerVariants;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    @Override
    public String toString() {
        return "QuizeQuastion{" +
                "number='" + number + '\'' +
                ", quastionDescription='" + quastionDescription + '\'' +
                ", help='" + help + '\'' +
                ", answersTypes=" + answersTypes +
                ", answerVariants=" + answerVariants +
                '}';
    }
}
