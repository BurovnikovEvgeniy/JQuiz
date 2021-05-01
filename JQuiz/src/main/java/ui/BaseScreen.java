package ui;

import javax.swing.*;
import java.awt.*;

public abstract class BaseScreen extends JComponent {
    protected final int width;
    protected final int height;
    protected final Color buttonColor;
    protected final Color exitButtonColor;
    protected final Font font;
    protected final Dimension exitButtonSize;
    protected Dimension componentSize;
    protected JFrame parentFrame;

    BaseScreen(JFrame parentFrame) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        this.height = (int) (screenSize.height * 0.5);
        this.width = (int) (this.height * 1.5);
        this.buttonColor = new Color(199, 211, 221);
        this.exitButtonColor = new Color(214, 201, 201);
        this.componentSize = new Dimension((int) (this.width * 0.3), (int) (this.height * 0.1));
        this.exitButtonSize = new Dimension((int) (this.width * 0.16), (int) (this.height * 0.1));
        this.parentFrame = parentFrame;
        this.font = new Font("TimesRoman", Font.PLAIN, 14);
    }

    protected JButton createButton(int x, int y, String name) {
        JButton button = new JButton(name);
        button.setBackground(buttonColor);
        button.setSize(componentSize);
        button.setLocation(x, y);
        button.setFont(font);
        button.setMargin(new Insets(0, 0, 0, 0));
        return button;
    }

    protected JButton createBackButton() {
        JButton button = new JButton("Назад");
        button.setBackground(buttonColor);
        button.setSize(exitButtonSize);
        button.setLocation(width - exitButtonSize.width - 20, 20);
        button.setFont(font);
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
        button.setFont(font);
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
        textField.setFont(font);
        return textField;
    }

    protected JPasswordField createPasswordField(int x, int y) {
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setEchoChar('*');
        passwordField.setSize(componentSize);
        passwordField.setToolTipText("Введите пароль");
        passwordField.setHorizontalAlignment(SwingConstants.CENTER);
        passwordField.setLocation(x, y);
        passwordField.setFont(font);
        return passwordField;
    }

    protected JTextArea createTextArea(String text, int width, int height, int x, int y) {
        JTextArea textArea = new JTextArea(text);
        textArea.setFont(font);
        textArea.setSize(width, height);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setLocation(x, y);
        textArea.setBackground(parentFrame.getContentPane().getBackground());
        return textArea;
    }
}
