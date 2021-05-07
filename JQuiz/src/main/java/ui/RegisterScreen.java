package ui;

import core.LogInManager;
import model.User;

import javax.swing.*;
import java.awt.*;

public class RegisterScreen extends BaseScreen {
    private final int componentX;
    private final boolean isAdmin;
    private final LogInManager logInManager;

    RegisterScreen(JFrame parent, boolean isAdmin) {
        super(parent);
        this.componentX = (width - componentSize.width) / 2;
        this.isAdmin = isAdmin;
        this.logInManager = new LogInManager();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (isAdmin) {
            drawGreetingString(g2);
        }
        String description = "Логин:";
        drawDescription(
                g2,
                description,
                (int) (height * 0.28) + componentSize.height / 2
        );
        JTextField loginField = createTextField(
                componentX,
                (int) (height * 0.28),
                "Введите логин",
                20,
                componentSize.width,
                componentSize.height
        );
        if (isAdmin) {
            loginField.setText("admin");
            loginField.setEditable(false);
        }
        add(loginField);
        description = "Пароль:";
        drawDescription(
                g2,
                description,
                (int) (height * 0.42) + componentSize.height / 2
        );
        JPasswordField passwordField = createPasswordField(componentX, (int) (height * 0.42));
        add(passwordField);
        JButton button = createButton(componentX, (int) (height * 0.56), "Зарегистрироваться");
        button.addActionListener(actionEvent -> {
            try {
                parentFrame.getContentPane().remove(0);
                if (isAdmin) {
                    logInManager.registerAdmin(loginField.getText(), new String(passwordField.getPassword()));
                    parentFrame.add(new LogInScreen(parentFrame));
                } else {
                    logInManager.register(loginField.getText(), new String(passwordField.getPassword()));
                    parentFrame.add(new TestScreen(parentFrame, new User(loginField.getText(), new String(passwordField.getPassword()))));
                }
                parentFrame.repaint();
                parentFrame.setVisible(true);
            } catch (RuntimeException e) {
                SwingUtilities.invokeLater(() -> new NotificationFrame(e.getMessage()).setVisible(true));
            }
        });
        add(button);
    }

    private void drawDescription(Graphics2D g2, String description, int y) {
        g2.setFont(font14);
        FontMetrics fm = g2.getFontMetrics();
        int x = (width - componentSize.width) / 2 - fm.stringWidth(description) - 10;
        g2.drawString(description, x, y);
    }

    private void drawGreetingString(Graphics2D g2) {
        g2.setFont(font20);
        FontMetrics fm = g2.getFontMetrics();
        String greeting = "Для начала работы создайте аккаунт администратора";
        int posX = (width - fm.stringWidth(greeting)) / 2;
        int posY = (int) (height * 0.14);
        g2.drawString(greeting, posX, posY);
    }
}
