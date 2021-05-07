package ui;

import core.QuestionManager;
import core.exceptions.QuestionException;
import model.Question;

import javax.swing.*;
import java.awt.*;

public class AddQuestionScreen extends BaseScreen {
    private final int margin;
    private final int descriptionWidth;
    private final Dimension textAreaSize;
    private final String[] descriptions;
    private final JTextField[] fields;
    private final QuestionManager questionManager;
    private int currentHeight;

    AddQuestionScreen(JFrame parent, QuestionManager questionManager) {
        super(parent);
        this.margin = 10;
        this.descriptionWidth = (int) (width * 0.2);
        this.textAreaSize = new Dimension(width - descriptionWidth - 4 * this.margin, (int) (height * 0.1));
        this.descriptions = new String[]{"Вопрос", "Вариант 1", "Вариант 2", "Вариант 3", "Вариант 4", "Верный индекс"};
        this.fields = new JTextField[descriptions.length];
        this.questionManager = questionManager;
        this.currentHeight = 20 + exitButtonSize.height + this.margin;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawUsername(g2);
        drawBackButton();
        for (int i = 0; i < descriptions.length; i++) {
            fields[i] = createTextField(
                    descriptionWidth + 2 * margin,
                    currentHeight,
                    descriptions[i],
                    400,
                    textAreaSize.width,
                    textAreaSize.height
            );
            add(fields[i]);
            drawDescription(g2, descriptions[i] + ":");
            currentHeight += textAreaSize.height + margin;
        }
        drawSubmitButton();
        currentHeight = 20 + exitButtonSize.height + margin;
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

    private void drawDescription(Graphics2D g2, String description) {
        g2.setFont(font14);
        g2.drawString(description, margin, currentHeight + textAreaSize.height / 2);
    }

    private void drawSubmitButton() {
        JButton submitButton = createButton(width - componentSize.width - 20, currentHeight, "Добавить");
        submitButton.addActionListener(actionEvent -> {
            for (JTextField field : fields) {
                if (field.getText().length() == 0) {
                    SwingUtilities.invokeLater(() -> new NotificationFrame("Вы заполнили не все поля!").setVisible(true));
                    return;
                }
            }
            String[] variants = new String[]{fields[1].getText(), fields[2].getText(), fields[3].getText(), fields[4].getText()};
            Question question = new Question(fields[0].getText(), variants, Integer.parseInt(fields[5].getText()));
            try {
                questionManager.addQuestion(question);
            } catch (QuestionException e) {
                SwingUtilities.invokeLater(() -> new NotificationFrame(e.getMessage()).setVisible(true));
            }
        });
        add(submitButton);
    }
}
