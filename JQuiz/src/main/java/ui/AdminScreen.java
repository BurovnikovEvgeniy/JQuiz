package ui;

import javax.swing.*;
import java.awt.*;

public class AdminScreen extends BaseScreen {
    private final int buttonX;

    AdminScreen(JFrame parent) {
        super(parent);
        this.componentSize = new Dimension((int) (width * 0.35), (int) (height * 0.1));
        this.buttonX = (width - componentSize.width) / 2;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawExitButton();
        drawShowQuestionsButton();
        drawAddQuestionButton();
        drawDeleteQuestionButton();
        drawDeleteResultButton();
    }

    private void drawShowQuestionsButton() {
        JButton showQuestions = createButton(buttonX, (int) (height * 0.22), "Посмотреть базу вопросов");
        showQuestions.addActionListener(actionEvent -> {
            parentFrame.getContentPane().remove(0);  //todo scroll
            parentFrame.add(new QuestionsTableScreen(parentFrame));
            parentFrame.setVisible(true);
        });
        add(showQuestions);
    }

    private void drawAddQuestionButton() {
        JButton addQuestion = createButton(buttonX, (int) (height * 0.37), "Добавить вопрос");
        addQuestion.addActionListener(actionEvent -> {
            parentFrame.getContentPane().remove(0);  //todo scroll
            parentFrame.add(new AddQuestionScreen(parentFrame));
            parentFrame.setVisible(true);
        });
        add(addQuestion);
    }

    private void drawDeleteQuestionButton() {
        JButton deleteQuestion = createButton(buttonX, (int) (height * 0.52), "Удалить вопрос");
        deleteQuestion.addActionListener(actionEvent -> {
            parentFrame.getContentPane().remove(0);  //todo scroll
            parentFrame.add(new DeleteQuestionScreen(parentFrame));
            parentFrame.setVisible(true);
        });
        add(deleteQuestion);
    }

    private void drawDeleteResultButton() {
        JButton deleteResult = createButton(buttonX, (int) (height * 0.67), "Удалить запись результата");
        deleteResult.addActionListener(actionEvent -> {
            parentFrame.getContentPane().remove(0);  //todo scroll
            parentFrame.add(new DeleteResultScreen(parentFrame));
            parentFrame.setVisible(true);
        });
        add(deleteResult);
    }
}
