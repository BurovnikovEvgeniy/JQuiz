package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private static final Color BACKGROUND_COLOR = new Color(232, 238, 242);

    public MainFrame() {
        setTitle("JQuiz");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int height = (int) (screenSize.height * 0.5);
        int width = (int) (height * 1.5);
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        JComponent component = new LogInScreen(this);
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
        component.setAlignmentY(Component.CENTER_ALIGNMENT);
        getContentPane().setBackground(BACKGROUND_COLOR);
        add(component);
    }
}
