package ui;

import core.LogInManager;
import model.User;

import javax.swing.*;
import java.awt.*;

class LogInScreen extends BaseScreen {

    LogInScreen(JFrame parent) {
        super(parent);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        setSize(width, height);
        drawGreetingString(g2);
        JTextField loginField = createUserNameField((width - componentSize.width) / 2, (int) (height * 0.28), "Введите логин");
        add(loginField);
        JPasswordField passwordField = createPasswordField((width - componentSize.width) / 2, (int) (height * 0.42));
        add(passwordField);
        JButton enterButton = createButton((width - componentSize.width) / 2, (int) (height * 0.56), "Войти");
        enterButton.addActionListener(actionEvent -> {
            String message = "";
            LogInManager logInManager = new LogInManager();
            if (!logInManager.isValidEnteredData(loginField.getText(), new String(passwordField.getPassword()), message)) {
                SwingUtilities.invokeLater(() -> new NotificationFrame(message).setVisible(true));
            } else {
                parentFrame.getContentPane().remove(0);
                if (logInManager.isAdmin(loginField.getText(), new String(passwordField.getPassword()))) {
                    parentFrame.add(new AdminScreen(parentFrame));
                } else {
                    parentFrame.add(new TestScreen(parentFrame, new User(loginField.getText(), new String(passwordField.getPassword()))));
                }
                parentFrame.setVisible(true);
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
        int posX = (width - fm.stringWidth(greeting)) / 2;
        int posY = (int) (height * 0.14);
        g2.drawString(greeting, posX, posY);
    }

    private void drawRegisterButton() {
        JButton registerButton = createButton((width - 2 * componentSize.width - 40) / 2, (int) (height * 0.7), "Зарегистрироваться");
        registerButton.addActionListener(actionEvent -> {
            parentFrame.getContentPane().remove(0);
            parentFrame.add(new RegisterScreen(parentFrame));
            parentFrame.setVisible(true);
        });
        add(registerButton);
    }

    private void drawGuestButton() {
        JButton guestButton = createButton(width / 2 + 20, (int) (height * 0.7), "Войти как гость");
        guestButton.addActionListener(actionEvent -> {
            parentFrame.getContentPane().remove(0);
            parentFrame.add(new TestScreen(parentFrame, null));
            parentFrame.setVisible(true);
        });
        add(guestButton);
    }
}
