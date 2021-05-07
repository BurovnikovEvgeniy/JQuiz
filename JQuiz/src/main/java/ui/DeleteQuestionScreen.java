package ui;

import core.QuestionManager;
import model.Question;

import javax.swing.*;
import java.awt.*;

public class DeleteQuestionScreen extends BaseScreen {
    private final Question[] questions;
    private final int cellWidth;
    private final int stroke;
    private final QuestionManager questionManager;
    private int currentHeight;

    DeleteQuestionScreen(JFrame parent, QuestionManager questionManager) {
        super(parent);
        this.componentSize = new Dimension((int) (width * 0.16), (int) (height * 0.1));
        this.questions = new QuestionManager().getAllQuestions();
        this.cellWidth = width - componentSize.width - 30;
        this.stroke = 1;
        this.questionManager = questionManager;
        this.currentHeight = 30 + exitButtonSize.height;
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
            JButton deleteButton = createButton(width - componentSize.width - 20, currentHeight - stroke, "Удалить");
            deleteButton.addActionListener(actionEvent -> {
                questionManager.deleteQuestion(question);
                parentFrame.getContentPane().remove(0);
                parentFrame.add(new DeleteQuestionScreen(parentFrame, questionManager));
                parentFrame.setVisible(true);
            });
            add(deleteButton);
            drawQuestion(g2, question);
        }
        currentHeight = 30 + exitButtonSize.height;
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
        g2.drawLine(0, currentHeight, cellWidth, currentHeight);
        currentHeight += 30;
        String name = "Вопрос";
        g2.setFont(font14);
        FontMetrics fm = g2.getFontMetrics();
        int x = (cellWidth - fm.stringWidth(name)) / 2;
        int y = currentHeight - (30 - font14.getSize()) / 2;
        g2.drawString(name, x, y);
        g2.drawLine(0, currentHeight, cellWidth, currentHeight);
        currentHeight += stroke;
        g2.drawLine(cellWidth, currentHeight - 30, cellWidth, currentHeight);
    }

    private void drawQuestion(Graphics2D g2, Question question) {
        FontMetrics fm = g2.getFontMetrics();
        int margin = 10;
        int linesNumber = (fm.stringWidth(question.getQuestion()) / (cellWidth - 2 * margin)) + 1;
        JTextArea textArea = createTextArea(
                question.getQuestion(),
                cellWidth - 2 * margin,
                Math.max(linesNumber * fm.getHeight(), componentSize.height),
                margin,
                currentHeight
        );
        add(textArea);
        currentHeight += textArea.getHeight();
        g2.drawLine(0, currentHeight, cellWidth, currentHeight);
        currentHeight += stroke;
        g2.drawLine(cellWidth, currentHeight - textArea.getHeight(), cellWidth, currentHeight);
    }
}
