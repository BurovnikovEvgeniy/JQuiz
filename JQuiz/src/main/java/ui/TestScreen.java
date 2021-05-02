package ui;

import core.QuestionManager;
import model.Question;
import model.Result;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class TestScreen extends BaseScreen {
    private final User user;
    private final int questionsCount;
    private final Question[] questions;
    private final boolean[] answers;
    private int currentQuestion;

    TestScreen(JFrame parent, User user) {
        super(parent);
        this.user = user;
        /*this.questionsCount = 2;
        this.questions = new QuestionManager().getQuestions(questionsCount);*/
        this.questions = new QuestionManager().getAllQuestions();
        this.questionsCount = this.questions.length;
        this.answers = new boolean[questionsCount];
        currentQuestion = 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawExitButton();
        drawGreetingString(g2);
        drawShowTableButton();
        drawStartTestButton();
    }

    private void drawGreetingString(Graphics2D g2) {
        Font font = new Font("TimesRoman", Font.PLAIN, 20);
        g2.setFont(font);
        FontMetrics fm = g2.getFontMetrics();
        String greeting = "Проверь свои знания языка программирования";
        int x = ((width - fm.stringWidth(greeting)) / 2);
        int y = (int) (height * 0.32);
        g2.drawString(greeting, x, y);
        String language = "Java!";
        x = ((width - fm.stringWidth(language)) / 2);
        y = (int) (height * 0.32) + 20;
        g2.drawString(language, x, y);
    }

    private void drawShowTableButton() {
        JButton showTable = createButton(width / 2 - 20 - componentSize.width, (int) (height * 0.67), "Посмотреть таблицу");
        showTable.addActionListener(actionEvent -> {
            parentFrame.getContentPane().remove(0);
            parentFrame.add(new ResultsTableScreen(parentFrame, user));  //todo scroll
            parentFrame.setVisible(true);
        });
        add(showTable);
    }

    private void drawStartTestButton() {
        JButton startTest = createButton(width / 2 + 20, (int) (height * 0.67), "Пройти тест");
        startTest.addActionListener(actionEvent -> drawQuestion(true));
        add(startTest);
    }

    void drawQuestion(boolean answer) {
        if (currentQuestion < questionsCount) {
            if (currentQuestion > 0) {
                answers[currentQuestion - 1] = answer;
                parentFrame.getContentPane().remove(1);
            } else {
                parentFrame.getContentPane().getComponent(0).setVisible(false);
            }
            parentFrame.add(new QuestionScreen(parentFrame, user, currentQuestion + 1, questionsCount, questions[currentQuestion]));
            parentFrame.setVisible(true);
            currentQuestion++;
        } else {
            answers[currentQuestion - 1] = answer;
            Result result = new Result(user.getName(), new Date(), answers); //"" -> user.getName() выводилось пустое имя
            parentFrame.getContentPane().remove(1);
            parentFrame.getContentPane().remove(0);
            parentFrame.add(new ResultScreen(parentFrame, user, result, questionsCount));
            parentFrame.setVisible(true);
        }
    }
}
