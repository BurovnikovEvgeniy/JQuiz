package ui;

import core.LogInManager;
import model.User;

import javax.swing.*;
import java.awt.*;

public class RegisterScreen extends BaseScreen {
    private final int componentX;

    RegisterScreen(JFrame parent) {
        super(parent);
        this.componentX = (width - componentSize.width) / 2;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setSize(WIDTH, HEIGHT);
        JTextField loginField = createUserNameField(componentX, (int) (height * 0.28), "Введите логин");
        add(loginField);
        JPasswordField passwordField = createPasswordField(componentX, (int) (height * 0.42));
        add(passwordField);
        JButton button = createButton(componentX, (int) (HEIGHT * 0.56), "Зарегистрироваться");
        button.addActionListener(actionEvent -> {
            String message = "";
            if (!new LogInManager().register(loginField.getText(), new String(passwordField.getPassword()), message)) {
                SwingUtilities.invokeLater(() -> new NotificationFrame(message).setVisible(true));
            } else {
                parentFrame.getContentPane().remove(0);
                parentFrame.add(new TestScreen(parentFrame, new User(loginField.getText(), new String(passwordField.getPassword()))));
                parentFrame.repaint();
                parentFrame.setVisible(true);
            }
        });
        add(button);
    }
}
