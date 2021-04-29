package ui;

import javax.swing.*;
import java.awt.*;

public class NotificationFrame extends JFrame {

    NotificationFrame(String message) {
        setTitle("Notification");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int height = (int) (screenSize.height * 0.2);
        int width = height * 2;
        setSize(width, height);
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
