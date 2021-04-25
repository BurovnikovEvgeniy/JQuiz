package ui;

import core.Application;
import model.User;

import javax.swing.*;
import java.awt.*;

class LogInScreen extends JComponent {
    private final int WIDTH;
    private final int HEIGHT;
    private final Color BUTTON_COLOR;
    private final Dimension COMPONENTS_SIZE;
    private final JFrame PARENT_FRAME;

    LogInScreen(int width, int height, Color buttonColor, Dimension componentsSize, JFrame parent) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.BUTTON_COLOR = buttonColor;
        this.COMPONENTS_SIZE = componentsSize;
        this.PARENT_FRAME = parent;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        setSize(WIDTH, HEIGHT);
        drawGreetingString(g2);
        JTextField loginField = new JTextField(20);
        loginField.setSize(COMPONENTS_SIZE);
        loginField.setHorizontalAlignment(SwingConstants.CENTER);
        loginField.setToolTipText("Введите логин");  //???
        int posX = ((WIDTH - COMPONENTS_SIZE.width)/2);
        int posY = (int)(HEIGHT * 0.28);
        loginField.setLocation(posX, posY);
        Font font = new Font("TimesRoman", Font.PLAIN, 14);
        loginField.setFont(font);
        add(loginField);
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setEchoChar('*');
        passwordField.setSize(COMPONENTS_SIZE);
        passwordField.setToolTipText("Введите пароль");  //???
        passwordField.setHorizontalAlignment(SwingConstants.CENTER);
        posX = ((WIDTH - COMPONENTS_SIZE.width)/2);
        posY = (int)(HEIGHT * 0.42);
        passwordField.setLocation(posX, posY);
        passwordField.setFont(font);
        add(passwordField);
        JButton enterButton = new JButton("Войти");
        enterButton.setBackground(BUTTON_COLOR);
        enterButton.setSize(COMPONENTS_SIZE);
        enterButton.setFont(font);
        posX = ((WIDTH - COMPONENTS_SIZE.width)/2);
        posY = (int)(HEIGHT * 0.56);
        enterButton.setLocation(posX, posY);
        enterButton.addActionListener(actionEvent -> {
            String message = "";
            if (!Application.isValidEnteredData(loginField.getText(), new String(passwordField.getPassword()), message)) {
                SwingUtilities.invokeLater(()->new NotificationFrame(message).setVisible(true));
            }
            else {
                PARENT_FRAME.getContentPane().remove(0);
                PARENT_FRAME.add(new TestScreen(WIDTH, HEIGHT, BUTTON_COLOR, COMPONENTS_SIZE, PARENT_FRAME,
                        new User(loginField.getText(), new String(passwordField.getPassword()))));
                PARENT_FRAME.setVisible(true);
            }
        });
        add(enterButton);
        drawRegisterButton();
        drawGuestButton();
    }

    private void drawGreetingString(Graphics2D g2) {
        Font font = new Font("TimesRoman", Font.PLAIN, 20);
        g2.setFont(font);
        FontMetrics fm = g2.getFontMetrics();
        String greeting = "Добро пожаловать в систему тестирования!";
        int posX = ((WIDTH - fm.stringWidth(greeting))/2);
        int posY = (int)(HEIGHT * 0.14);
        g2.drawString(greeting, posX, posY);
    }

    private void drawRegisterButton() {
        JButton button = new JButton("Зарегистрироваться");
        button.setBackground(BUTTON_COLOR);
        button.setSize(COMPONENTS_SIZE);
        int posX = ((WIDTH - 2 * COMPONENTS_SIZE.width - 40)/2);
        int posY = (int)(HEIGHT * 0.7);
        button.setLocation(posX, posY);
        Font font = new Font("TimesRoman", Font.PLAIN, 14);
        button.setFont(font);
        button.setMargin(new Insets(0,0,0,0));
        button.addActionListener(actionEvent -> {
            PARENT_FRAME.getContentPane().remove(0);
            PARENT_FRAME.add(new RegisterScreen(WIDTH, HEIGHT, BUTTON_COLOR, COMPONENTS_SIZE, PARENT_FRAME));
            PARENT_FRAME.setVisible(true);
        });
        add(button);
    }

    private void drawGuestButton() {
        JButton button = new JButton("Войти как гость");
        button.setBackground(BUTTON_COLOR);
        button.setSize(COMPONENTS_SIZE);
        int posX = WIDTH / 2 + 20;
        int posY = (int)(HEIGHT * 0.7);
        button.setLocation(posX, posY);
        Font font = new Font("TimesRoman", Font.PLAIN, 14);
        button.setFont(font);
        button.addActionListener(actionEvent -> {
            PARENT_FRAME.getContentPane().remove(0);
            PARENT_FRAME.add(new TestScreen(WIDTH, HEIGHT, BUTTON_COLOR, COMPONENTS_SIZE, PARENT_FRAME, null));
            PARENT_FRAME.setVisible(true);
        });
        add(button);
    }
}
