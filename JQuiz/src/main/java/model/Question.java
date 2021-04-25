package model;

import java.io.Serializable;
import java.util.List;

public class Question implements Serializable {
    private String question;
    private String[] variants;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setVariants(String[] variants) {
        this.variants = variants;
    }

    public void setCorrectAnswerIndex(int correctAnswerIndex) {
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String[] getVariants() {
        return variants;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    private int correctAnswerIndex;

    public Question(String question, String[] variants, int correctAnswerIndex) {
        this.question = question;
        this.variants = variants;
        this.correctAnswerIndex = correctAnswerIndex;
    }
}
