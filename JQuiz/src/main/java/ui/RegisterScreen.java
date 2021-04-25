package ui;

import core.Application;
import model.User;

import javax.swing.*;
import java.awt.*;

public class RegisterScreen extends JComponent {
    private final int WIDTH;
    private final int HEIGHT;
    private final Color BUTTON_COLOR;
    private final Dimension COMPONENTS_SIZE;
    private final JFrame PARENT_FRAME;

    RegisterScreen(int width, int height, Color buttonColor, Dimension componentsSize, JFrame parent) {
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
        setSize(WIDTH, HEIGHT);
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
        JButton button = new JButton("Зарегистрироваться");
        button.setBackground(BUTTON_COLOR);
        button.setSize(COMPONENTS_SIZE);
        posX = ((WIDTH - COMPONENTS_SIZE.width)/2);
        posY = (int)(HEIGHT * 0.56);
        button.setLocation(posX, posY);
        button.setFont(font);
        button.setMargin(new Insets(0,0,0,0));
        button.addActionListener(actionEvent -> {
            String message = "";
            if (!Application.register(loginField.getText(), new String(passwordField.getPassword()), message)) {
                SwingUtilities.invokeLater(()->new NotificationFrame(message).setVisible(true));
            }
            else {
                PARENT_FRAME.getContentPane().remove(0);
                PARENT_FRAME.add(new TestScreen(WIDTH, HEIGHT, BUTTON_COLOR, COMPONENTS_SIZE, PARENT_FRAME,
                        new User(loginField.getText(), new String(passwordField.getPassword()))));
                PARENT_FRAME.repaint();
                PARENT_FRAME.setVisible(true);
            }
        });
        add(button);
    }
}
