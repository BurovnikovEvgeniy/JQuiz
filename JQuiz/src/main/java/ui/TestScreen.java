package ui;

import core.Application;
import model.Question;
import model.Results;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class TestScreen extends JComponent {
    private final int WIDTH;
    private final int HEIGHT;
    private final Color BUTTON_COLOR;
    private final Dimension COMPONENTS_SIZE;
    private final Dimension EXIT_BUTTON_SIZE = new Dimension(100, 40);
    private final JFrame PARENT_FRAME;
    private final Color VARIANT_COLOR = new Color(214, 201, 201);
    private final int QUESTIONS_COUNT = 2;
    private final Question[] QUESTIONS;
    private final User USER;
    private final boolean[] answers;
    private int currentQuestion;

    TestScreen(int width, int height, Color buttonColor, Dimension componentsSize, JFrame parent, User user) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.BUTTON_COLOR = buttonColor;
        this.COMPONENTS_SIZE = componentsSize;
        this.PARENT_FRAME = parent;
        this.USER = user;
        this.QUESTIONS = Application.getQuestions(QUESTIONS_COUNT);
        this.answers = new boolean[QUESTIONS_COUNT];
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

    private void drawExitButton() {
        JButton exit = new JButton();
        exit.setText("Выйти");
        exit.setBackground(VARIANT_COLOR);
        exit.setSize(EXIT_BUTTON_SIZE);
        int posX = WIDTH - 20 - EXIT_BUTTON_SIZE.width;
        int posY = 20;
        exit.setLocation(posX, posY);
        Font font = new Font("TimesRoman", Font.PLAIN, 14);
        exit.setFont(font);
        exit.addActionListener(actionEvent -> {
            PARENT_FRAME.getContentPane().remove(0);
            PARENT_FRAME.add(new LogInScreen(WIDTH, HEIGHT, BUTTON_COLOR, COMPONENTS_SIZE, PARENT_FRAME));
            PARENT_FRAME.repaint();
            PARENT_FRAME.setVisible(true);
        });
        add(exit);
    }

    private void drawGreetingString(Graphics2D g2) {
        Font font = new Font("TimesRoman", Font.PLAIN, 20);
        g2.setFont(font);
        FontMetrics fm = g2.getFontMetrics();
        String greeting = "Проверь свои знания языка программирования";
        int posX = ((WIDTH - fm.stringWidth(greeting))/2);
        int posY = (int)(HEIGHT * 0.32);
        g2.drawString(greeting, posX, posY);
        String language = "Java!";
        posX = ((WIDTH - fm.stringWidth(language))/2);
        posY = (int)(HEIGHT * 0.32) + 20;
        g2.drawString(language, posX, posY);
    }

    private void drawShowTableButton() {
        JButton showTable = new JButton();
        showTable.setText("Посмотреть таблицу");
        showTable.setBackground(BUTTON_COLOR);
        showTable.setSize(COMPONENTS_SIZE);
        int posX = WIDTH / 2 - 20 - COMPONENTS_SIZE.width;
        int posY = (int)(HEIGHT * 0.67);
        showTable.setLocation(posX, posY);
        Font font = new Font("TimesRoman", Font.PLAIN, 14);
        showTable.setFont(font);
        showTable.setMargin(new Insets(0,0,0,0));
        showTable.addActionListener(actionEvent -> {
            PARENT_FRAME.getContentPane().remove(0);
            PARENT_FRAME.add(new TableScreen());
            PARENT_FRAME.setVisible(true);
        });
        add(showTable);
    }

    private void drawStartTestButton() {
        JButton startTest = new JButton();
        startTest.setText("Пройти тест");
        startTest.setBackground(BUTTON_COLOR);
        startTest.setSize(COMPONENTS_SIZE);
        int posX = WIDTH / 2 + 20;
        int posY = (int)(HEIGHT * 0.67);
        startTest.setLocation(posX, posY);
        Font font = new Font("TimesRoman", Font.PLAIN, 14);
        startTest.setFont(font);
        startTest.addActionListener(actionEvent -> drawQuestion(true));
        add(startTest);
    }

    void drawQuestion(boolean answer) {
        if (currentQuestion < QUESTIONS_COUNT) {
            if (currentQuestion > 0) {
                answers[currentQuestion - 1] = answer;
                PARENT_FRAME.getContentPane().remove(1);
            }
            else {
                PARENT_FRAME.getContentPane().getComponent(0).setVisible(false);
            }
            PARENT_FRAME.add(new QuestionScreen(WIDTH, HEIGHT, BUTTON_COLOR, VARIANT_COLOR, COMPONENTS_SIZE, PARENT_FRAME, USER,
                    currentQuestion + 1, QUESTIONS_COUNT, QUESTIONS[currentQuestion]));
            PARENT_FRAME.setVisible(true);
            currentQuestion++;
        }
        else {
            answers[currentQuestion - 1] = answer;
            Results result = new Results("", new Date(), answers);
            PARENT_FRAME.getContentPane().remove(1);
            PARENT_FRAME.getContentPane().remove(0);
            PARENT_FRAME.add(new ResultScreen(WIDTH, HEIGHT, BUTTON_COLOR, COMPONENTS_SIZE, PARENT_FRAME, USER, result, QUESTIONS_COUNT));
            PARENT_FRAME.setVisible(true);
        }
    }
}
