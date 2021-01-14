
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;

import models.Expense;
import models.User;

public class App {
    /* App data */
    private static User user;


    /* App components */
    private static final JFrame FRAME = new JFrame();

    private static final JPanel START_PAGE = new JPanel();
    private static final JPanel HOME_PAGE = new JPanel();
    private static final JPanel ADD_EXPENSE_PAGE = new JPanel();
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

        try {
            submitBtn.addActionListener(e -> {
                user.setUsername(usernameInput.getText());
            });

            switchToPage(HOME_PAGE);
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }

    private static void initHomePage() {
        int totalExpenses = 0, totalSaving = 0, totalIncome = 0;

        for (Expense exp : user.getExpenses()) {
            totalExpenses += exp.getSpend();
            totalSaving += exp.getSaving().getAmount();
            totalIncome += exp.getIncome().getSalary();
        }

        HOME_PAGE.setLayout(null);

        int width = 610; int height = 300;

        HOME_PAGE.setPreferredSize(new Dimension(width, height));

        JLabel title = new JLabel("Homepage");
        title.setFont(new Font("Calibri", Font.BOLD, 25));
        title.setBounds(width/2 - 60, 50, 120, 30);
        HOME_PAGE.add(title);

        height -= 100;
        
        int marginLeft = 200;

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(marginLeft, height/2, 100, 30);
        HOME_PAGE.add(usernameLabel);
        JLabel usernameValue = new JLabel("Edel");
        usernameValue.setBounds(marginLeft + 100, height/2, 200, 30);
        HOME_PAGE.add(usernameValue);

        JLabel totalExpensesLabel = new JLabel("Expenses:");
        totalExpensesLabel.setBounds(marginLeft, height/2 + 30, 100, 30);
        HOME_PAGE.add(totalExpensesLabel);
        JLabel totalExpensesValue = new JLabel("RM" + totalExpenses);
        totalExpensesValue.setBounds(marginLeft + 100, height/2 + 30, 200, 30);
        HOME_PAGE.add(totalExpensesValue);

        JLabel totalSavingLabel = new JLabel("Saving:");
        totalSavingLabel.setBounds(marginLeft, height/2 + 60, 100, 30);
        HOME_PAGE.add(totalSavingLabel);
        JLabel totalSavingValue = new JLabel("RM" + totalSaving);
        totalSavingValue.setBounds(marginLeft + 100, height/2 + 60, 200, 30);
        HOME_PAGE.add(totalSavingValue);

        JLabel salaryLabel = new JLabel("Income:");
        salaryLabel.setBounds(marginLeft, height/2 + 90, 100, 30);
        HOME_PAGE.add(salaryLabel);
        JLabel salaryValue = new JLabel("RM" + totalIncome);
        salaryValue.setBounds(marginLeft + 100, height/2 + 90, 200, 30);
        HOME_PAGE.add(salaryValue);

        /* actions */
        height += 100;

        JButton viewExpensesBtn = new JButton("View expenses");
        viewExpensesBtn.setBounds(width/2 - 150, height - 50, 130, 30);

        JButton viewIncomeBtn = new JButton("Add expense");
        viewIncomeBtn.setBounds(width/2 + 50, height - 50, 130, 30);

        HOME_PAGE.add(viewExpensesBtn);
        HOME_PAGE.add(viewIncomeBtn);
    }

    private static void initExpenseListPage() {
        EXPENSE_LIST_PAGE.setLayout(null);

        int width = 500; int height = 600;

        EXPENSE_LIST_PAGE.setPreferredSize(new Dimension(width, height));

        JLabel title = new JLabel("Expenses List");
        title.setFont(new Font("Calibri", Font.BOLD, 25));
        title.setBounds(width/2 - 70, 25, 140, 30);
        EXPENSE_LIST_PAGE.add(title);

        JPanel listContainer = new JPanel();
        listContainer.setLayout(null);
        listContainer.setPreferredSize(new Dimension(width, 0));
        JScrollPane scrollPane = new JScrollPane(listContainer);
        scrollPane.setPreferredSize(new Dimension(width, height - 200));
        scrollPane.setBounds(0, 100, width, height - 200);
        scrollPane.getVerticalScrollBar().setUnitIncrement(6);

        /* test loop */
        for(int x = 0; x < 30; x++) {
            JLabel label = new JLabel(x + " - Test");
            label.setBounds(10, x * 100, width, 80);
            JLabel label2 = new JLabel("wow");
            label2.setBounds(100, x * 100, width, 80);

            listContainer.setPreferredSize(new Dimension(width, 100 + (x * 100)));
            listContainer.add(label);
            listContainer.add(label2);
        }

        EXPENSE_LIST_PAGE.add(scrollPane);

        /* actions button */
        JButton backBtn = new JButton("Back");
        backBtn.setBounds((width/2) - 150, height - 75, 100, 30);
        JButton newBtn = new JButton("New expense");
        newBtn.setBounds((width/2), height - 75, 140, 30);

        EXPENSE_LIST_PAGE.add(backBtn);
        EXPENSE_LIST_PAGE.add(newBtn);
    }

    private static void initPages() {
        initStartPage();
        initHomePage();
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
        /* initilize data */
        user = new User("");
        
        FRAME.setTitle("Expense tracker");
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initPages();

        switchToPage(HOME_PAGE);

        show();
    }
}
