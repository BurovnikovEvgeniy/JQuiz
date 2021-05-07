package ui;

import javax.swing.*;
import java.awt.*;

public abstract class BaseScreen extends JComponent {
    protected final int width;
    protected final int height;
    protected final Color buttonColor;
    protected final Color exitButtonColor;
    protected final Color backgroundColor;
    protected final Font font14;
    protected final Font font16;
    protected final Font font20;
    protected final Font font32;
    protected final Dimension exitButtonSize;
    protected final int usernameX;
    protected final int usernameY;
    protected Dimension componentSize;
    protected JFrame parentFrame;

    BaseScreen(JFrame parentFrame) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        this.height = (int) (screenSize.height * 0.5);
        this.width = (int) (this.height * 1.5) - 20;
        this.buttonColor = new Color(199, 211, 221);
        this.exitButtonColor = new Color(214, 201, 201);
        this.backgroundColor = new Color(232, 238, 242);
        this.componentSize = new Dimension((int) (this.width * 0.3), (int) (this.height * 0.1));
        this.exitButtonSize = new Dimension((int) (this.width * 0.16), (int) (this.height * 0.1));
        this.parentFrame = parentFrame;
        this.font14 = new Font("TimesRoman", Font.PLAIN, 14);
        this.font16 = new Font("TimesRoman", Font.PLAIN, 16);
        this.font20 = new Font("TimesRoman", Font.PLAIN, 20);
        this.font32 = new Font("TimesRoman", Font.PLAIN, 32);
        this.usernameX = 10;
        this.usernameY = 12;
    }

    protected JButton createButton(int x, int y, String name) {
        JButton button = new JButton(name);
        button.setBackground(buttonColor);
        button.setSize(componentSize);
        button.setLocation(x, y);
        button.setFont(font14);
        button.setMargin(new Insets(0, 0, 0, 0));
        return button;
    }

    protected JButton createBackButton() {
        JButton button = new JButton("Назад");
        button.setBackground(buttonColor);
        button.setSize(exitButtonSize);
        button.setLocation(width - exitButtonSize.width - 20, 20);
        button.setFont(font14);
        button.setMargin(new Insets(0, 0, 0, 0));
        return button;
    }


    protected void drawExitButton() {
        JButton button = new JButton("Выйти");
        button.setBackground(exitButtonColor);
        button.setSize(exitButtonSize);
        int x = width - exitButtonSize.width - 20;
        int y = 20;
        button.setLocation(x, y);
        button.setFont(font14);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.addActionListener(actionEvent -> {
            parentFrame.getContentPane().remove(0);
            parentFrame.add(new LogInScreen(parentFrame));
            parentFrame.repaint();
            parentFrame.setVisible(true);
        });
        add(button);
    }

    protected JTextField createTextField(int x, int y, String toolTipText, int symbolNumbers, int width, int height) {
        JTextField textField = new JTextField(symbolNumbers);
        textField.setSize(width, height);
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setToolTipText(toolTipText);
        textField.setLocation(x, y);
        textField.setFont(font14);
        return textField;
    }

    protected JPasswordField createPasswordField(int x, int y) {
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setEchoChar('*');
        passwordField.setSize(componentSize);
        passwordField.setToolTipText("Введите пароль");
        passwordField.setHorizontalAlignment(SwingConstants.CENTER);
        passwordField.setLocation(x, y);
        passwordField.setFont(font14);
        return passwordField;
    }

    protected JTextArea createTextArea(String text, int width, int height, int x, int y) {
        JTextArea textArea = new JTextArea(text);
        textArea.setFont(font14);
        textArea.setSize(width, height);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setLocation(x, y);
        textArea.setBackground(parentFrame.getContentPane().getBackground());
        return textArea;
    }

    protected void addScrollScreen(JComponent component) {
        JScrollPane scrollPane = new JScrollPane(component, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setSize(width, height);
        JScrollBar scrollBar = scrollPane.createVerticalScrollBar();
        scrollBar.setPreferredSize(new Dimension(20, 100));
        scrollPane.add(scrollBar);
        scrollPane.getViewport().setBackground(backgroundColor);
        parentFrame.add(scrollPane);
    }
}
