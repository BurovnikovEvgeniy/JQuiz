package ui;

import model.Question;
import model.User;

import javax.swing.*;
import java.awt.*;

public class QuestionScreen extends JComponent {
    private final int WIDTH;
    private final int HEIGHT;
    private final Color BUTTON_COLOR;
    private final Color VARIANT_COLOR;
    private final Dimension COMPONENTS_SIZE;
    private final Dimension VARIANT_SIZE = new Dimension(200, 50);
    private final JFrame PARENT_FRAME;
    private final int NUMBER;
    private final int QUESTIONS_COUNT;
    private final Question QUESTION;
    private final User USER;
    private final JButton[] variants;
    private int chosenIndex;

    QuestionScreen(int width, int height, Color buttonColor, Color variantColor, Dimension componentsSize, JFrame parent, User user,
                   int number, int count, Question question) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.BUTTON_COLOR = buttonColor;
        this.VARIANT_COLOR = variantColor;
        this.COMPONENTS_SIZE = componentsSize;
        this.PARENT_FRAME = parent;
        this.NUMBER = number;
        this.QUESTIONS_COUNT = count;
        this.QUESTION = question;
        this.USER = user;
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
        String serialNumber = NUMBER + "/" + QUESTIONS_COUNT;
        int posX = ((WIDTH - fm.stringWidth(serialNumber)) / 2);
        int posY = 20;
        g2.drawString(serialNumber, posX, posY);
    }

    private void drawQuestion() {
        JTextArea textArea = new JTextArea(QUESTION.getQuestion());
        textArea.setSize(2 * VARIANT_SIZE.width, 80);
        textArea.setBackground(PARENT_FRAME.getContentPane().getBackground());
        Font font = new Font("TimesRoman", Font.PLAIN, 16);
        textArea.setFont(font);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setLocation((WIDTH - textArea.getSize().width) / 2, 50);
        add(textArea);
    }

    private void drawVariants() {
        for (int i = 0; i < 4; i++) {
            variants[i] = new JButton(QUESTION.getVariants()[i]);
            variants[i].setBackground(VARIANT_COLOR);
            variants[i].setSize(VARIANT_SIZE);
            int posX = (WIDTH - 2 * VARIANT_SIZE.width - 20) / 2 + (VARIANT_SIZE.width + 20) * (i % 2);
            int posY = HEIGHT / 2 - VARIANT_SIZE.height + (VARIANT_SIZE.height + 20) * (i / 2);
            variants[i].setLocation(posX, posY);
            Font font = new Font("TimesRoman", Font.PLAIN, 14);
            variants[i].setFont(font);
            int finalI = i;
            variants[i].addActionListener(actionEvent -> {
                if (chosenIndex != -1) {
                    SwingUtilities.invokeLater(() -> new NotificationFrame("You have already chosen the answer!").setVisible(true));
                } else {
                    chosenIndex = finalI;
                    if (finalI == QUESTION.getCorrectAnswerIndex()) {
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
        JButton menu = new JButton("Главное меню");
        menu.setBackground(BUTTON_COLOR);
        menu.setSize(COMPONENTS_SIZE);
        int posX = 40;
        int posY = HEIGHT - 20 - 2 * COMPONENTS_SIZE.height;
        menu.setLocation(posX, posY);
        Font font = new Font("TimesRoman", Font.PLAIN, 14);
        menu.setFont(font);
        menu.addActionListener(actionEvent -> {
            PARENT_FRAME.getContentPane().remove(1);
            PARENT_FRAME.getContentPane().remove(0);
            PARENT_FRAME.add(new TestScreen(WIDTH, HEIGHT, BUTTON_COLOR, COMPONENTS_SIZE, PARENT_FRAME, USER));
            PARENT_FRAME.setVisible(true);
        });
        add(menu);
    }

    private void drawNextButton() {
        JButton nextQuestion;
        if (NUMBER == QUESTIONS_COUNT) {
            nextQuestion = new JButton("Завершить");
        } else {
            nextQuestion = new JButton("Следующий вопрос");
        }
        nextQuestion.setBackground(BUTTON_COLOR);
        nextQuestion.setSize(COMPONENTS_SIZE);
        int posX = WIDTH - 40 - COMPONENTS_SIZE.width;
        int posY = HEIGHT - 20 - 2 * COMPONENTS_SIZE.height;
        nextQuestion.setLocation(posX, posY);
        Font font = new Font("TimesRoman", Font.PLAIN, 14);
        nextQuestion.setFont(font);
        nextQuestion.setMargin(new Insets(0, 0, 0, 0));
        nextQuestion.addActionListener(actionEvent -> {
            if (chosenIndex == -1) {
                SwingUtilities.invokeLater(() -> new NotificationFrame("You did not choose the answer!").setVisible(true));
            } else {
                TestScreen ts = (TestScreen) PARENT_FRAME.getContentPane().getComponent(0);
                ts.drawQuestion(chosenIndex == QUESTION.getCorrectAnswerIndex());
            }
        });
        add(nextQuestion);
    }
}
