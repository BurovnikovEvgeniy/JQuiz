package ui;

import core.LogInManager;
import core.QuestionManager;

import javax.swing.*;
import java.awt.*;

public class AdminScreen extends BaseScreen {
    private final int buttonX;
    private final String[] items;
    private final LogInManager logInManager;
    private final QuestionManager questionManager;

    AdminScreen(JFrame parent) {
        super(parent);
        this.componentSize = new Dimension((int) (width * 0.37), (int) (height * 0.1));
        this.buttonX = (width - componentSize.width) / 2;
        this.items = new String[]{"Сменить пароль"};
        this.logInManager = new LogInManager();
        this.questionManager = new QuestionManager();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawSettingsBox();
        drawUsername(g2);
        drawExitButton();
        drawShowQuestionsButton();
        drawAddQuestionButton();
        drawDeleteQuestionButton();
        drawDeleteResultButton();
    }

    private void drawSettingsBox() {
        JComboBox comboBox = new JComboBox(items);
        comboBox.setToolTipText("Настройки");
        comboBox.setFont(font14);
        comboBox.addActionListener(actionEvent -> {
            JComboBox box = (JComboBox) actionEvent.getSource();
            String item = (String) box.getSelectedItem();
            if (item != null && item.equals("Сменить пароль")) {
                parentFrame.getContentPane().remove(0);
                parentFrame.add(new ChangePasswordScreen(parentFrame, logInManager.getAdmin()));
                parentFrame.setVisible(true);
            }
        });
        FontMetrics fm = getFontMetrics(font14);
        comboBox.setLocation(usernameX + fm.stringWidth("Администратор") + 10, 0);
        comboBox.setSize(fm.stringWidth(items[0] + 20), 20);
        add(comboBox);
    }

    private void drawUsername(Graphics2D g2) {
        g2.setFont(font14);
        g2.drawString("Администратор", usernameX, usernameY);
    }

    private void drawShowQuestionsButton() {
        JButton showQuestions = createButton(buttonX, (int) (height * 0.22), "Посмотреть базу вопросов");
        showQuestions.addActionListener(actionEvent -> {
            parentFrame.getContentPane().remove(0);
            addScrollScreen(new QuestionsTableScreen(parentFrame, questionManager));
            parentFrame.setVisible(true);
        });
        add(showQuestions);
    }

    private void drawAddQuestionButton() {
        JButton addQuestion = createButton(buttonX, (int) (height * 0.37), "Добавить вопрос");
        addQuestion.addActionListener(actionEvent -> {
            parentFrame.getContentPane().remove(0);
            parentFrame.add(new AddQuestionScreen(parentFrame, questionManager));
            parentFrame.setVisible(true);
        });
        add(addQuestion);
    }

    private void drawDeleteQuestionButton() {
        JButton deleteQuestion = createButton(buttonX, (int) (height * 0.52), "Удалить вопрос");
        deleteQuestion.addActionListener(actionEvent -> {
            parentFrame.getContentPane().remove(0);
            addScrollScreen(new DeleteQuestionScreen(parentFrame, questionManager));
            parentFrame.setVisible(true);
        });
        add(deleteQuestion);
    }

    private void drawDeleteResultButton() {
        JButton deleteResult = createButton(buttonX, (int) (height * 0.67), "Удалить запись результата");
        deleteResult.addActionListener(actionEvent -> {
            parentFrame.getContentPane().remove(0);
            addScrollScreen(new DeleteResultScreen(parentFrame));
            parentFrame.setVisible(true);
        });
        add(deleteResult);
    }
}
