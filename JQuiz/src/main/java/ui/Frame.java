package ui;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
    private static final Color BACKGROUND_COLOR = new Color(232, 238,242);
    private static final Color BUTTON_COLOR = new Color(199, 211, 221);
    private static final Dimension BUTTON_SIZE = new Dimension(150, 40);

    public Frame() {
        setTitle("JQuiz");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        JComponent component = new LogInScreen(WIDTH, HEIGHT, BUTTON_COLOR, BUTTON_SIZE, this);
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
        component.setAlignmentY(Component.CENTER_ALIGNMENT);
        getContentPane().setBackground(BACKGROUND_COLOR);
        add(component);
    }
}
