
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;

public class App {
    private static final JFrame FRAME = new JFrame();

    private static final JPanel START_PAGE = new JPanel();
    private static JButton START_PAGE_SUBMIT_BTN;

    private static final JPanel EXPENSE_LIST_PAGE = new JPanel();

    private static void initStartPage() {
        START_PAGE.setLayout(null);

        int width = 500; int height = 300;

        START_PAGE.setPreferredSize(new Dimension(width, height));

        JLabel title = new JLabel("Expense tracker");
        title.setFont(new Font("Calibri", Font.BOLD, 25));
        title.setBounds(width/2 - 90, 50, 180, 30);
        START_PAGE.add(title);

        JLabel usernameLabel = new JLabel("Enter username");
        usernameLabel.setBounds(width/2 - 47, height/2 - 30, 94, 30);
        START_PAGE.add(usernameLabel);

        JTextField usernameInput = new JTextField("");
        usernameInput.setBounds(width/2 - 70, height/2, 140, 30);
        START_PAGE.add(usernameInput);

        JButton submitBtn = new JButton("Enter");
        submitBtn.setBounds(width/2 - 40, height/2 + 35, 80, 20);
        START_PAGE.add(submitBtn);

        START_PAGE_SUBMIT_BTN = submitBtn;
    }

    private static void initExpenseListPage() {
        EXPENSE_LIST_PAGE.setLayout(null);

        int width = 500; int height = 600;

        EXPENSE_LIST_PAGE.setPreferredSize(new Dimension(width, height));

        JLabel title = new JLabel("Expenses List");
        title.setFont(new Font("Calibri", Font.BOLD, 25));
        title.setBounds(width/2 - 70, 25, 140, 30);
        EXPENSE_LIST_PAGE.add(title);

        /* test loop */
    }

    private static void initPages() {
        initStartPage();
        initExpenseListPage();
    }

    public static void show() {
        FRAME.setVisible(true);
    }

    public static void switchToPage(JPanel page) {
        FRAME.setContentPane(page);
        FRAME.pack();
        FRAME.setLocationRelativeTo(null);
    }

    public static void main(String[] args) throws Exception {
        
        FRAME.setTitle("Expense tracker");
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initPages();

        switchToPage(EXPENSE_LIST_PAGE);
        START_PAGE_SUBMIT_BTN.addActionListener(e -> {
            switchToPage(EXPENSE_LIST_PAGE);
        });

        show();
    }
}
