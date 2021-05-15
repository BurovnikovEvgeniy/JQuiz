package ui;

import core.LogInManager;
import core.exceptions.EmptyPasswordException;
import core.exceptions.EmptyUsernameException;
import core.exceptions.WrongPasswordException;
import model.User;

import javax.swing.*;
import java.awt.*;

public class ChangePasswordScreen extends BaseScreen {
    private final User user;
    private final LogInManager logInManager;

    ChangePasswordScreen(JFrame parent, User user) {
        super(parent);
        this.user = user;
        this.logInManager = new LogInManager();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawBackButton();
        String description = "Текущий пароль:";
        drawDescription(
                g2,
                description,
                (int) (height * 0.28) + componentSize.height / 2
        );
        JPasswordField oldPassword = createPasswordField((width - componentSize.width) / 2, (int) (height * 0.28));
        add(oldPassword);
        description = "Новый пароль:";
        drawDescription(
                g2,
                description,
                (int) (height * 0.42) + componentSize.height / 2
        );
        JPasswordField newPassword = createPasswordField((width - componentSize.width) / 2, (int) (height * 0.42));
        add(newPassword);
        JButton submitButton = createButton((width - componentSize.width) / 2, (int) (height * 0.56), "Подтвердить");
        submitButton.addActionListener(actionEvent -> {
            if (user.getPassword().equals(new String(oldPassword.getPassword()))) {
                try {
                    logInManager.changePassword(user.getName(), new String(newPassword.getPassword()));
                    parentFrame.getContentPane().remove(0);
                    if (user.getName().equals("admin")) {
                        parentFrame.add(new AdminScreen(parentFrame));
                    } else {
                        parentFrame.add(new TestScreen(parentFrame, user));
                    }
                    parentFrame.setVisible(true);
                } catch (EmptyPasswordException | EmptyUsernameException e) {
                    SwingUtilities.invokeLater(() -> new NotificationFrame(e.getMessage()).setVisible(true));
                }
            } else {
                SwingUtilities.invokeLater(() -> new NotificationFrame("Текущий пароль введен неверно!").setVisible(true));
            }
        });
        add(submitButton);
    }

    private void drawDescription(Graphics2D g2, String description, int y) {
        g2.setFont(font14);
        FontMetrics fm = g2.getFontMetrics();
        int x = (width - componentSize.width) / 2 - fm.stringWidth(description) - 10;
        g2.drawString(description, x, y);
    }

    private void drawBackButton() {
        JButton backButton = createBackButton();
        backButton.addActionListener(actionEvent -> {
            parentFrame.getContentPane().remove(0);
            parentFrame.add(new TestScreen(parentFrame, user));
            parentFrame.setVisible(true);
        });
        add(backButton);
    }
}
