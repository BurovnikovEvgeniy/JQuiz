package model;

import java.io.Serializable;

public class Question implements Serializable {
    private final String question;
    private final String[] answers;
    private final int correctAnswer;

    public Question(String question, String[] variants, int correctAnswerIndex) {
        this.question = question;
        this.answers = variants;
        this.correctAnswer = correctAnswerIndex;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getAnswers() {
        return answers;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

}
