package ui;

import core.ResultManager;
import model.Result;

import javax.swing.*;
import java.awt.*;

public class DeleteResultScreen extends BaseScreen {
    private final Result[] results;
    private final int firstCellWidth;
    private final int secondCellWidth;
    private final int thirdCellWidth;
    private final int stroke;
    private final int margin;
    private int currentHeight;
    private int currentCellHeight;

    DeleteResultScreen(JFrame parent) {
        super(parent);
        this.componentSize = new Dimension((int) (width * 0.16), (int) (height * 0.1));
        this.results = new ResultManager().getAllResults();
        this.firstCellWidth = (int) ((width - componentSize.width - 30) * 0.4);
        this.secondCellWidth = (int) ((width - componentSize.width - 30) * 0.15);
        this.thirdCellWidth = width - componentSize.width - 30 - this.firstCellWidth - this.secondCellWidth;
        this.stroke = 1;
        this.margin = 10;
        this.currentHeight = componentSize.height + 30;
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
        for (Result result : results) {
            JButton deleteButton = createButton(width - componentSize.width - 20, currentHeight - stroke, "Удалить");
            deleteButton.addActionListener(actionEvent -> {
                new ResultManager().deleteResult(result);
                parentFrame.getContentPane().remove(0);
                parentFrame.add(new DeleteResultScreen(parentFrame));
                parentFrame.setVisible(true);
            });
            add(deleteButton);
            drawResult(g2, result);
        }
        currentHeight = componentSize.height + 30;
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
        g2.drawLine(0, currentHeight, firstCellWidth + secondCellWidth + thirdCellWidth, currentHeight);
        String name = "Имя пользователя";
        g2.setFont(font14);
        FontMetrics fm = g2.getFontMetrics();
        int x = (firstCellWidth - fm.stringWidth(name)) / 2;
        int y = currentHeight + 30 - (30 - font14.getSize()) / 2;
        g2.drawString(name, x, y);
        name = "Дата";
        x = firstCellWidth + secondCellWidth + (thirdCellWidth - fm.stringWidth(name)) / 2;
        g2.drawString(name, x, y);
        name = "Баллы";
        x = firstCellWidth + (secondCellWidth - fm.stringWidth(name)) / 2;
        g2.drawString(name, x, y);
        drawTableCarcass(g2);
    }

    private void drawTableCarcass(Graphics2D g2) {
        g2.drawLine(firstCellWidth, currentHeight, firstCellWidth, currentHeight + currentCellHeight);
        g2.drawLine(
                firstCellWidth + secondCellWidth,
                currentHeight,
                firstCellWidth + secondCellWidth,
                currentHeight + currentCellHeight
        );
        g2.drawLine(
                firstCellWidth + secondCellWidth + thirdCellWidth,
                currentHeight,
                firstCellWidth + secondCellWidth + thirdCellWidth,
                currentHeight + currentCellHeight
        );
        currentHeight += currentCellHeight;
        g2.drawLine(0, currentHeight, firstCellWidth + secondCellWidth + thirdCellWidth, currentHeight);
        currentHeight += stroke;
    }

    private void drawResult(Graphics2D g2, Result result) {
        FontMetrics fm = g2.getFontMetrics();
        currentCellHeight = Math.max(
                Math.max(((fm.stringWidth(result.getName()) / (firstCellWidth - 2 * margin)) + 1) * fm.getHeight(),
                        ((fm.stringWidth(String.valueOf(result.getScore())) / (secondCellWidth - 2 * margin)) + 1) * fm.getHeight()),
                Math.max(((fm.stringWidth(result.getDate().toString()) / (thirdCellWidth - 2 * margin)) + 1) * fm.getHeight(),
                        componentSize.height)
        );
        drawUsername(result);
        drawScore(result);
        drawDate(result);
        drawTableCarcass(g2);
    }

    private void drawUsername(Result result) {
        JTextArea textArea = createTextArea(
                result.getName(),
                firstCellWidth - 2 * margin,
                currentCellHeight,
                margin,
                currentHeight
        );
        add(textArea);
    }

    private void drawScore(Result result) {
        JTextArea textArea = createTextArea(
                String.valueOf(result.getScore()),
                secondCellWidth - 2 * margin,
                currentCellHeight,
                firstCellWidth + margin,
                currentHeight
        );
        add(textArea);
    }

    private void drawDate(Result result) {
        JTextArea textArea = createTextArea(
                result.getDate().toString(),
                thirdCellWidth - 2 * margin,
                currentCellHeight,
                firstCellWidth + secondCellWidth + margin,
                currentHeight
        );
        add(textArea);
    }
}
