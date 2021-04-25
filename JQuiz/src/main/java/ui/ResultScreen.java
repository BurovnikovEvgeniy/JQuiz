package ui;

import core.Application;
import model.Results;
import model.User;

import javax.swing.*;
import java.awt.*;

public class ResultScreen extends JComponent {
    private final int WIDTH;
    private final int HEIGHT;
    private final Color BUTTON_COLOR;
    private final Dimension COMPONENTS_SIZE;
    private final JFrame PARENT_FRAME;
    private final User USER;
    private final Results RESULTS;
    private final int QUESTIONS_COUNT;
    private boolean isSave = false;

    ResultScreen(int width, int height, Color buttonColor, Dimension componentsSize, JFrame parent, User user,
                 Results results, int count) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.BUTTON_COLOR = buttonColor;
        this.COMPONENTS_SIZE = componentsSize;
        this.PARENT_FRAME = parent;
        this.USER = user;
        this.RESULTS = results;
        this.QUESTIONS_COUNT = count;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawScore(g2);
        if (USER == null) {
            drawMenuButton((WIDTH - 2 * COMPONENTS_SIZE.width - 30) / 2, (int)(HEIGHT * 0.6));
            drawRestartButton(WIDTH / 2 + 30, (int)(HEIGHT * 0.6));
        }
        else {
            drawToTableButton((WIDTH - 3 * COMPONENTS_SIZE.width - 60) / 2, (int)(HEIGHT * 0.6));
            drawMenuButton((WIDTH - 3 * COMPONENTS_SIZE.width - 60) / 2 + COMPONENTS_SIZE.width + 30, (int)(HEIGHT * 0.6));
            drawRestartButton((WIDTH - 3 * COMPONENTS_SIZE.width - 60) / 2 + 2 * COMPONENTS_SIZE.width + 60, (int)(HEIGHT * 0.6));
        }
    }

    private void drawScore(Graphics2D g2) {
        Font font = new Font("TimesRoman", Font.PLAIN, 32);
        g2.setFont(font);
        FontMetrics fm = g2.getFontMetrics();
        String score = RESULTS.getScore() + "/" + QUESTIONS_COUNT + "!";
        int posX = ((WIDTH - fm.stringWidth(score))/2);
        int posY = (int)(HEIGHT * 0.3);
        g2.drawString(score, posX, posY);
    }

    private void drawMenuButton(int posX, int posY) {
        JButton menu = new JButton("Главное меню");
        menu.setBackground(BUTTON_COLOR);
        menu.setSize(COMPONENTS_SIZE);
        menu.setLocation(posX, posY);
        Font font = new Font("TimesRoman", Font.PLAIN, 14);
        menu.setFont(font);
        menu.addActionListener(actionEvent -> {
            PARENT_FRAME.getContentPane().remove(0);
            PARENT_FRAME.add(new TestScreen(WIDTH, HEIGHT, BUTTON_COLOR, COMPONENTS_SIZE, PARENT_FRAME, USER));
            PARENT_FRAME.setVisible(true);
        });
        add(menu);
    }

    private void drawRestartButton(int posX, int posY) {
        JButton restart = new JButton("Пройти заново");
        restart.setBackground(BUTTON_COLOR);
        restart.setSize(COMPONENTS_SIZE);
        restart.setLocation(posX, posY);
        Font font = new Font("TimesRoman", Font.PLAIN, 14);
        restart.setFont(font);
        restart.addActionListener(actionEvent -> {
            PARENT_FRAME.getContentPane().remove(0);
            TestScreen ts = new TestScreen(WIDTH, HEIGHT, BUTTON_COLOR, COMPONENTS_SIZE, PARENT_FRAME, USER);
            PARENT_FRAME.add(ts);
            ts.drawQuestion(true);
            PARENT_FRAME.setVisible(true);
        });
        add(restart);
    }

    private void drawToTableButton(int posX, int posY) {
        JButton toTable = new JButton("Сохранить результат");
        toTable.setBackground(BUTTON_COLOR);
        toTable.setSize(COMPONENTS_SIZE);
        toTable.setLocation(posX, posY);
        Font font = new Font("TimesRoman", Font.PLAIN, 14);
        toTable.setFont(font);
        toTable.setMargin(new Insets(0, 0, 0, 0));
        toTable.addActionListener(actionEvent -> {
            if (!isSave) {
                Application.saveResults(RESULTS);
                isSave = true;
            }
            else {
                SwingUtilities.invokeLater(()->new NotificationFrame("Result has already been saved!").setVisible(true));
            }
        });
        add(toTable);
    }
}
