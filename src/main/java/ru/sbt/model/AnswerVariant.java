package ru.sbt.model;

public class AnswerVariant {
    private String quastion;
    private boolean isAnswer;

    public String getQuastion() {
        return quastion;
    }

    public void setQuastion(String quastion) {
        this.quastion = quastion;
    }

    public boolean isAnswer() {
        return isAnswer;
    }

    public void setAnswer(boolean answer) {
        this.isAnswer = answer;
    }

    @Override
    public String toString() {
        return "AnswerVariant{" +
                "quastion='" + quastion + '\'' +
                ", isAnswer=" + isAnswer +
                '}';
    }
}
