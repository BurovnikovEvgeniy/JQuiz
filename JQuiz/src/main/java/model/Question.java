package model;

import java.util.List;

public class Question {
    private String question;
    private List<String> variants;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setVariants(List<String> variants) {
        this.variants = variants;
    }

    public void setCorrectAnswerIndex(int correctAnswerIndex) {
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public List<String> getVariants() {
        return variants;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    private int correctAnswerIndex;

    public Question(String question, List<String> variants, int correctAnswerIndex) {
        this.question = question;
        this.variants = variants;
        this.correctAnswerIndex = correctAnswerIndex;
    }
}
