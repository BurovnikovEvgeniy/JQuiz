package ui;

import core.ResultManager;
import model.Result;
import model.User;

import javax.swing.*;
import java.awt.*;

public class ResultScreen extends BaseScreen {
    private final User user;
    private final Result result;
    private final int questionsCount;
    private boolean isSave = false;

    ResultScreen(JFrame parent, User user, Result results, int count) {
        super(parent);
        this.user = user;
        this.result = results;
        this.questionsCount = count;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawScore(g2);
        if (user == null) {
            drawMenuButton((width - 2 * componentSize.width - 30) / 2, (int) (height * 0.6));
            drawRestartButton(width / 2 + 30, (int) (height * 0.6));
        } else {
            drawToTableButton((width - 3 * componentSize.width - 60) / 2, (int) (height * 0.6));
            drawMenuButton((width - 3 * componentSize.width - 60) / 2 + componentSize.width + 30, (int) (height * 0.6));
            drawRestartButton((width - 3 * componentSize.width - 60) / 2 + 2 * componentSize.width + 60, (int) (height * 0.6));
        }
    }

    private void drawScore(Graphics2D g2) {
        Font font = new Font("TimesRoman", Font.PLAIN, 32);
        g2.setFont(font);
        FontMetrics fm = g2.getFontMetrics();
        String score = result.getScore() + "/" + questionsCount + "!";
        int posX = ((width - fm.stringWidth(score)) / 2);
        int posY = (int) (height * 0.3);
        g2.drawString(score, posX, posY);
    }

    private void drawMenuButton(int x, int y) {
        JButton menu = createButton(x, y, "Главное меню");
        menu.addActionListener(actionEvent -> {
            parentFrame.getContentPane().remove(0);
            parentFrame.add(new TestScreen(parentFrame, user));
            parentFrame.setVisible(true);
        });
        add(menu);
    }

    private void drawRestartButton(int x, int y) {
        JButton restart = createButton(x, y, "Пройти заново");
        restart.addActionListener(actionEvent -> {
            parentFrame.getContentPane().remove(0);
            TestScreen ts = new TestScreen(parentFrame, user);
            parentFrame.add(ts);
            ts.drawQuestion(true);
            parentFrame.setVisible(true);
        });
        add(restart);
    }

    private void drawToTableButton(int x, int y) {
        JButton toTable = createButton(x, y, "Сохранить результат");
        toTable.addActionListener(actionEvent -> {
            if (!isSave) {
                new ResultManager().saveResults(result);
                isSave = true;
            } else {
                SwingUtilities.invokeLater(() -> new NotificationFrame("Result has already been saved!").setVisible(true));
            }
        });
        add(toTable);
    }
}
