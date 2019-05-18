package ru.airo.model;

public class Result {
    private String time;
    private Integer questionsCount;
    private Integer correctAnswersCount;
    private Integer inCorrectAnswersCount;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getQuestionsCount() {
        return questionsCount;
    }

    public void setQuestionsCount(Integer questionsCount) {
        this.questionsCount = questionsCount;
    }

    public Integer getCorrectAnswersCount() {
        return correctAnswersCount;
    }

    public void setCorrectAnswersCount(Integer correctAnswersCount) {
        this.correctAnswersCount = correctAnswersCount;
    }

    public Integer getInCorrectAnswersCount() {
        return inCorrectAnswersCount;
    }

    public void setInCorrectAnswersCount(Integer inCorrectAnswersCount) {
        this.inCorrectAnswersCount = inCorrectAnswersCount;
    }
}
