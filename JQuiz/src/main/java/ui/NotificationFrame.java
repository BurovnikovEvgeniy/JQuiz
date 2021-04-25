package ui;

import javax.swing.*;
import java.awt.*;

public class NotificationFrame extends JFrame {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 150;

    NotificationFrame(String message) {
        setTitle("Notification");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setResizable(false);
        JLabel text = new JLabel(message);
        text.setSize(250, 100);
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setVerticalAlignment(JLabel.CENTER);
        text.setFont(new Font("TimesRoman", Font.PLAIN, 16));
        add(text);
        setVisible(true);
    }
}
