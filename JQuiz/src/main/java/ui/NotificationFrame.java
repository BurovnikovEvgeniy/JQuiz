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
        JTextArea text = new JTextArea(message);
        text.setSize(width - 40, height - 40);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setEditable(false);
        text.setFont(new Font("TimesRoman", Font.PLAIN, 16));
        text.setLocation(20, 20);
        add(text);
        setVisible(true);
    }
}
