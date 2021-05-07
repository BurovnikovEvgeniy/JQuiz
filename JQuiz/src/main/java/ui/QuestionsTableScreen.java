package ui;

import core.QuestionManager;
import model.Question;

import javax.swing.*;
import java.awt.*;

public class QuestionsTableScreen extends BaseScreen {
    private final Question[] questions;
    private final int questionFieldWidth;
    private final int answerFieldWidth;
    private final int correctAnswerFieldWidth;
    private final int stroke;
    private final int margin;
    private int currentHeight;
    private int currentCellHeight;

    QuestionsTableScreen(JFrame parent, QuestionManager questionManager) {
        super(parent);
        this.questions = questionManager.getAllQuestions();
        this.questionFieldWidth = (int) (width * 0.25);
        this.answerFieldWidth = (int) (width * 0.6) / 4;
        this.correctAnswerFieldWidth = width - questionFieldWidth - answerFieldWidth * 4;
        this.stroke = 1;
        this.margin = 5;
        this.currentHeight = exitButtonSize.height + 30;
        this.currentCellHeight = 30;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawUsername(g2);
        drawBackButton();
        drawTableHeader(g2);
        for (Question question : questions) {
            drawQuestion(g2, question);
        }
        currentHeight = exitButtonSize.height + 30;
        currentCellHeight = 30;
    }

    private void drawUsername(Graphics2D g2) {
        g2.setFont(font14);
        g2.drawString("Администратор", usernameX, usernameY);
    }

    private void drawBackButton() {
        JButton backButton = createBackButton();
        backButton.addActionListener(actionEvent -> {
            parentFrame.getContentPane().remove(0);
            parentFrame.add(new AdminScreen(parentFrame));
            parentFrame.setVisible(true);
        });
        add(backButton);
    }

    private void drawTableHeader(Graphics2D g2) {
        g2.setStroke(new BasicStroke(stroke));
        g2.drawLine(0, currentHeight, width, currentHeight);
        String name = "Вопрос";
        g2.setFont(font14);
        FontMetrics fm = g2.getFontMetrics();
        int x = (questionFieldWidth - fm.stringWidth(name)) / 2;
        int y = currentHeight + currentCellHeight - (currentCellHeight - font14.getSize()) / 2;
        g2.drawString(name, x, y);
        for (int i = 0; i < 4; i++) {
            name = "Вариант " + (i + 1);
            x = questionFieldWidth + answerFieldWidth * i + (answerFieldWidth - fm.stringWidth(name)) / 2;
            g2.drawString(name, x, y);
        }
        name = "Верный";
        x = questionFieldWidth + answerFieldWidth * 4 + (correctAnswerFieldWidth - 10 - fm.stringWidth(name)) / 2;
        g2.drawString(name, x, y);
        drawTableCarcass(g2);
    }

    private void drawTableCarcass(Graphics2D g2) {
        for (int i = 0; i < 5; i++) {
            g2.drawLine(
                    questionFieldWidth + answerFieldWidth * i,
                    currentHeight,
                    questionFieldWidth + answerFieldWidth * i,
                    currentHeight + currentCellHeight
            );
        }
        currentHeight += currentCellHeight;
        g2.drawLine(0, currentHeight, width, currentHeight);
        currentHeight += stroke;
    }

    private void drawQuestion(Graphics2D g2, Question question) {
        drawQuestionText(g2, question);
        drawAnswers(g2, question);
        drawCorrectIndex(g2, question);
        drawTableCarcass(g2);
    }

    private void drawQuestionText(Graphics2D g2, Question question) {
        FontMetrics fm = g2.getFontMetrics();
        int linesNumber = (fm.stringWidth(question.getQuestion()) / (questionFieldWidth - 2 * margin)) + 1;
        JTextArea textArea = createTextArea(
                question.getQuestion(),
                questionFieldWidth - 2 * margin,
                linesNumber * fm.getHeight(),
                margin,
                currentHeight
        );
        add(textArea);
        currentCellHeight = textArea.getHeight();
    }

    private void drawAnswers(Graphics2D g2, Question question) {
        FontMetrics fm = g2.getFontMetrics();
        for (int i = 0; i < question.getAnswers().length; i++) {
            int linesNumber = (fm.stringWidth(question.getAnswers()[i]) / (answerFieldWidth - 2 * margin)) + 1;
            JTextArea textArea = createTextArea(
                    question.getAnswers()[i],
                    answerFieldWidth - 2 * margin,
                    linesNumber * fm.getHeight(),
                    margin + questionFieldWidth + answerFieldWidth * i,
                    currentHeight
            );
            add(textArea);
            currentCellHeight = Math.max(textArea.getHeight(), currentCellHeight);
        }
    }

    private void drawCorrectIndex(Graphics2D g2, Question question) {
        FontMetrics fm = g2.getFontMetrics();
        JTextArea textArea = createTextArea(
                String.valueOf(question.getCorrectAnswer() + 1),
                correctAnswerFieldWidth - 2 * margin,
                fm.getHeight(),
                margin + questionFieldWidth + answerFieldWidth * 4,
                currentHeight
        );
        add(textArea);
    }
}
