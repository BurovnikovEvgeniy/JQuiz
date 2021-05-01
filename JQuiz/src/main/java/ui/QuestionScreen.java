package ui;

import model.Question;
import model.User;

import javax.swing.*;
import java.awt.*;

public class QuestionScreen extends BaseScreen {
    private final User user;
    private final Color variantColor;
    private final Dimension variantSize;
    private final int serialNumber;
    private final int questionsCount;
    private final Question question;
    private final JButton[] variants;
    private int chosenIndex;

    QuestionScreen(JFrame parent, User user, int number, int count, Question question) {
        super(parent);
        this.user = user;
        this.variantColor = new Color(214, 201, 201);
        this.variantSize = new Dimension((int) (width * 0.35), (int) (height * 0.13));
        this.serialNumber = number;
        this.questionsCount = count;
        this.question = question;
        this.variants = new JButton[4];
        this.chosenIndex = -1;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawSerialNumber(g2);
        drawQuestion();
        drawVariants();
        drawMenuButton();
        drawNextButton();
    }

    private void drawSerialNumber(Graphics2D g2) {
        Font font = new Font("TimesRoman", Font.PLAIN, 14);
        g2.setFont(font);
        FontMetrics fm = g2.getFontMetrics();
        String serialNumber = this.serialNumber + "/" + questionsCount;
        int posX = (width - fm.stringWidth(serialNumber)) / 2;
        int posY = 20;
        g2.drawString(serialNumber, posX, posY);
    }

    private void drawQuestion() {
        JTextArea textArea = new JTextArea(question.getQuestion());
        textArea.setSize(2 * variantSize.width, 80);
        textArea.setBackground(parentFrame.getContentPane().getBackground());
        Font font = new Font("TimesRoman", Font.PLAIN, 16);
        textArea.setFont(font);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setLocation((width - textArea.getSize().width) / 2, 50);
        textArea.setEditable(false);
        add(textArea);
    }

    private void drawVariants() {
        for (int i = 0; i < 4; i++) {
            variants[i] = new JButton(question.getAnswers()[i]);
            variants[i].setBackground(variantColor);
            variants[i].setSize(variantSize);
            int posX = (width - 2 * variantSize.width - 20) / 2 + (variantSize.width + 20) * (i % 2);
            int posY = height / 2 - variantSize.height + (variantSize.height + 20) * (i / 2);
            variants[i].setLocation(posX, posY);
            Font font = new Font("TimesRoman", Font.PLAIN, 14);
            variants[i].setFont(font);
            int finalI = i;
            variants[i].addActionListener(actionEvent -> {
                if (chosenIndex != -1) {
                    SwingUtilities.invokeLater(() -> new NotificationFrame("Вы уже выбрали ответ!").setVisible(true));
                } else {
                    chosenIndex = finalI;
                    if (finalI == question.getCorrectAnswer()) {
                        variants[finalI].setBackground(Color.GREEN);
                    } else {
                        variants[finalI].setBackground(Color.RED);
                    }
                }
            });
            add(variants[i]);
        }
    }

    private void drawMenuButton() {
        JButton menu = createButton(40, height - 20 - 2 * componentSize.height, "Главное меню");
        menu.addActionListener(actionEvent -> {
            parentFrame.getContentPane().remove(1);
            parentFrame.getContentPane().remove(0);
            parentFrame.add(new TestScreen(parentFrame, user));
            parentFrame.setVisible(true);
        });
        add(menu);
    }

    private void drawNextButton() {
        JButton nextQuestion;
        if (serialNumber == questionsCount) {
            nextQuestion = createButton(width - 40 - componentSize.width, height - 20 - 2 * componentSize.height, "Завершить");
        } else {
            nextQuestion = createButton(width - 40 - componentSize.width, height - 20 - 2 * componentSize.height,
                    "Следующий вопрос");
        }
        nextQuestion.addActionListener(actionEvent -> {
            if (chosenIndex == -1) {
                SwingUtilities.invokeLater(() -> new NotificationFrame("Вы не выбрали ответ!").setVisible(true));
            } else {
                TestScreen ts = (TestScreen) parentFrame.getContentPane().getComponent(0);
                ts.drawQuestion(chosenIndex == question.getCorrectAnswer());
            }
        });
        add(nextQuestion);
    }
}
