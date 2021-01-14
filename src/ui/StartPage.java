package ui;

import java.awt.Font;

import javax.swing.*;

public class StartPage {
    /* Page title */
    private static final String TITLE = "Expense Tracker";

    private static final int WIDTH = 500;
    private static final int HEIGHT = 300;

    private JFrame frame;

    private JTextField username;
    private JButton submit;

    public StartPage() {
        this.frame = new JFrame(TITLE);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);

        JPanel panel = initComponents();
        this.frame.getContentPane().add(panel);
    }

    private JPanel initComponents() {
        JPanel panel = new JPanel();

        panel.setLayout(null);

        JLabel title = new JLabel(TITLE);
        title.setFont(new Font("Calibri", Font.BOLD, 25));
        title.setBounds(WIDTH/2 - 90, 50, 180, 30);
        panel.add(title);

        JLabel usernameLabel = new JLabel("Enter username");
        usernameLabel.setBounds(WIDTH/2 - 47, HEIGHT/2 - 30, 94, 30);
        panel.add(usernameLabel);

        JTextField usernameInput = new JTextField("");
        usernameInput.setBounds(WIDTH/2 - 70, HEIGHT/2, 140, 30);
        panel.add(usernameInput);

        JButton submitBtn = new JButton("Enter");
        submitBtn.setBounds(WIDTH/2 - 40, HEIGHT/2 + 35, 80, 20);
        panel.add(submitBtn);

        this.username = usernameInput;
        this.submit = submitBtn;

        return panel;
    }

    public JTextField getUsernameInput() {
        return this.username;
    }

    public JButton getSubmitButton() {
        return this.submit;
    }

    public void show() {
        frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }
}
