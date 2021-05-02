package ui;

import core.LogInManager;
import core.QuestionManager;
import model.Question;
import model.User;

import javax.swing.*;
import java.awt.*;

public class AddQuestionScreen extends BaseScreen {
    private final int margin;
    private final int descriptionWidth;
    private final Dimension textAreaSize;
    private final String[] descriptions;
    private final JTextField[] fields;
    private int currentHeight;

    AddQuestionScreen(JFrame parent) {
        super(parent);
        this.margin = 10;
        this.descriptionWidth = (int) (width * 0.2);
        this.textAreaSize = new Dimension(width - descriptionWidth - 4 * this.margin, (int) (height * 0.1));
        this.descriptions = new String[]{"Вопрос", "Вариант 1", "Вариант 2", "Вариант 3", "Вариант 4", "Верный индекс"};
        this.fields = new JTextField[descriptions.length];
        this.currentHeight = 20 + exitButtonSize.height + this.margin;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
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
        g2.setFont(font);
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

            /*new QuestionManager().addQuestion(question);*/

            try {
                new QuestionManager().addQuestion(question);
            } catch (RuntimeException e) {
                SwingUtilities.invokeLater(() -> new NotificationFrame(e.getMessage()).setVisible(true));
            }
        });
        add(submitButton);
    }
}