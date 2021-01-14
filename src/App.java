
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;

import models.Category;
import models.Expense;
import models.Income;
import models.Saving;
import models.User;
import models.incometype.Fulltime;
import models.incometype.Parttime;

public class App {
    /* App data */
    private static User user;

    /* App components */
    private static final JFrame FRAME = new JFrame();

    private static final JPanel START_PAGE = new JPanel();
    private static final JPanel HOME_PAGE = new JPanel();
    private static final JPanel ADD_EXPENSE_PAGE = new JPanel();
    private static final JPanel EXPENSE_LIST_PAGE = new JPanel();
    private static final JPanel EXPENSE_LIST_CONTAINER = new JPanel();

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

                updateHomePage();

                switchToPage(HOME_PAGE);
            });
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }

    private static void initAddExpensePage() {
        ADD_EXPENSE_PAGE.setLayout(null);

        int width = 500; int height = 350;

        ADD_EXPENSE_PAGE.setPreferredSize(new Dimension(width, height));

        JLabel title = new JLabel("Add expense");
        title.setFont(new Font("Calibri", Font.BOLD, 25));
        title.setBounds(width/2 - 70, 50, 140, 30);
        ADD_EXPENSE_PAGE.add(title);

        int marginLeft = 100;
        height -= 100;

        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(marginLeft, height/2, 100, 30);
        ADD_EXPENSE_PAGE.add(categoryLabel);
        JTextField categoryValue = new JTextField();
        categoryValue.setBounds(marginLeft + 100, height/2, 200, 30);
        ADD_EXPENSE_PAGE.add(categoryValue);

        JLabel incomeTypeLabel = new JLabel("Income type:");
        incomeTypeLabel.setBounds(marginLeft, height/2 + 30, 100, 30);
        ADD_EXPENSE_PAGE.add(incomeTypeLabel);
        String[] types = {"Full time", "Part time"};
        JComboBox<String> incomeTypeValue = new JComboBox<>(types);
        incomeTypeValue.setSelectedIndex(0);
        incomeTypeValue.setBounds(marginLeft + 100, height/2 + 30, 200, 30);
        ADD_EXPENSE_PAGE.add(incomeTypeValue);

        JLabel incomeLabel = new JLabel("Income (RM):");
        incomeLabel.setBounds(marginLeft, height/2 + 60, 100, 30);
        ADD_EXPENSE_PAGE.add(incomeLabel);
        JTextField incomeValue = new JTextField();
        incomeValue.setBounds(marginLeft + 100, height/2 + 60, 200, 30);
        ADD_EXPENSE_PAGE.add(incomeValue);

        JLabel expensesLabel = new JLabel("Expense (RM):");
        expensesLabel.setBounds(marginLeft, height/2 + 90, 100, 30);
        ADD_EXPENSE_PAGE.add(expensesLabel);
        JTextField expensesValue = new JTextField();
        expensesValue.setBounds(marginLeft + 100, height/2 + 90, 200, 30);
        ADD_EXPENSE_PAGE.add(expensesValue);

        height += 100;

        JButton submitBtn = new JButton("Add expense");
        submitBtn.setBounds(width/2 - 70, height - 70, 140, 30);
        ADD_EXPENSE_PAGE.add(submitBtn);

        try {
            submitBtn.addActionListener(e -> {
                /* expense must be less than income */
                if (Double.parseDouble(incomeValue.getText()) < Double.parseDouble(expensesValue.getText())) {
                    throw new Error("expense must be less than income");
                }
                
                Category category = new Category(categoryValue.getText());
                Expense exp = new Expense(Double.parseDouble(expensesValue.getText()), category);

                Income income;
                /* full time as 0, part time as 1 */
                if (incomeTypeValue.getSelectedIndex() == 0) {
                    income = new Fulltime(Double.parseDouble(incomeValue.getText()));
                } else {
                    income = new Parttime(Double.parseDouble(incomeValue.getText()));
                }

                /* calc saving, income - expense */
                Saving saving = new Saving(income.getSalary() - exp.getSpend());

                exp.setIncome(income);
                exp.setSaving(saving);

                user.addExpense(exp);

                updateHomePage();

                switchToPage(HOME_PAGE);
            });
            
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void initExpenseListPage() {
        EXPENSE_LIST_PAGE.setLayout(null);

        int width = 800; int height = 600;

        EXPENSE_LIST_PAGE.setPreferredSize(new Dimension(width, height));

        JLabel title = new JLabel("Expenses List");
        title.setFont(new Font("Calibri", Font.BOLD, 25));
        title.setBounds(width/2 - 70, 25, 140, 30);
        EXPENSE_LIST_PAGE.add(title);

        JLabel headingNumber = new JLabel("No.");
        headingNumber.setBounds(10, 50, width, 80);
        JLabel headingIncome = new JLabel("Income");
        headingIncome.setBounds(80, 50, width, 80);
        JLabel headingIncomeType = new JLabel("Income type");
        headingIncomeType.setBounds(220, 50, width, 80);
        JLabel headingExpense = new JLabel("Expense");
        headingExpense.setBounds(320, 50, width, 80);
        JLabel headingSaving = new JLabel("Saving");
        headingSaving.setBounds(420, 50, width, 80);
        JLabel headingDate = new JLabel("Date");
        headingDate.setBounds(520, 50, width, 80);

        EXPENSE_LIST_PAGE.add(headingNumber);
        EXPENSE_LIST_PAGE.add(headingIncome);
        EXPENSE_LIST_PAGE.add(headingIncomeType);
        EXPENSE_LIST_PAGE.add(headingExpense);
        EXPENSE_LIST_PAGE.add(headingSaving);
        EXPENSE_LIST_PAGE.add(headingDate);

        EXPENSE_LIST_CONTAINER.setLayout(null);
        EXPENSE_LIST_CONTAINER.setPreferredSize(new Dimension(width, 0));
        JScrollPane scrollPane = new JScrollPane(EXPENSE_LIST_CONTAINER);
        scrollPane.setPreferredSize(new Dimension(width, height - 200));
        scrollPane.setBounds(0, 100, width, height - 200);
        scrollPane.getVerticalScrollBar().setUnitIncrement(6);

        /* list all expenses */
        int x = 1;
        for (Expense exp : user.getExpenses()) {
            JLabel label = new JLabel(x + ".");
            label.setBounds(10, x * 100, width, 80);
            JLabel label2 = new JLabel("RM" + exp.getSpend());
            label2.setBounds(100, x * 100, width, 80);

            EXPENSE_LIST_CONTAINER.setPreferredSize(new Dimension(width, 100 + (x * 100)));
            EXPENSE_LIST_CONTAINER.add(label);
            EXPENSE_LIST_CONTAINER.add(label2);

            ++x;
        }

        EXPENSE_LIST_PAGE.add(scrollPane);

        /* actions button */
        JButton backBtn = new JButton("Back");
        backBtn.setBounds((width/2) - 150, height - 75, 100, 30);
        JButton newBtn = new JButton("New expense");
        newBtn.setBounds((width/2), height - 75, 140, 30);

        backBtn.addActionListener(e -> {
            switchToPage(HOME_PAGE);
        });
        newBtn.addActionListener(e -> {
            switchToPage(ADD_EXPENSE_PAGE);
        });

        EXPENSE_LIST_PAGE.add(backBtn);
        EXPENSE_LIST_PAGE.add(newBtn);
    }

    private static void initPages() {
        initStartPage();
        initAddExpensePage();
        initExpenseListPage();
    }

    private static void updateHomePage() {
        HOME_PAGE.removeAll();

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
        JLabel usernameValue = new JLabel(user.getUsername());
        usernameValue.setBounds(marginLeft + 100, height/2, 200, 30);
        HOME_PAGE.add(usernameValue);

        JLabel totalExpensesLabel = new JLabel("Expenses:");
        totalExpensesLabel.setBounds(marginLeft, height/2 + 30, 100, 30);
        HOME_PAGE.add(totalExpensesLabel);
        JLabel totalExpensesValue = new JLabel("RM" + totalExpenses);
        totalExpensesValue.setBounds(marginLeft + 100, height/2 + 30, 200, 30);
        HOME_PAGE.add(totalExpensesValue);

        JLabel salaryLabel = new JLabel("Income:");
        salaryLabel.setBounds(marginLeft, height/2 + 60, 100, 30);
        HOME_PAGE.add(salaryLabel);
        JLabel salaryValue = new JLabel("RM" + totalIncome);
        salaryValue.setBounds(marginLeft + 100, height/2 + 60, 200, 30);
        HOME_PAGE.add(salaryValue);

        JLabel totalSavingLabel = new JLabel("Saving:");
        totalSavingLabel.setBounds(marginLeft, height/2 + 90, 100, 30);
        HOME_PAGE.add(totalSavingLabel);
        JLabel totalSavingValue = new JLabel("RM" + totalSaving);
        totalSavingValue.setBounds(marginLeft + 100, height/2 + 90, 200, 30);
        HOME_PAGE.add(totalSavingValue);

        /* actions */
        height += 100;

        JButton viewExpensesBtn = new JButton("View expenses");
        viewExpensesBtn.setBounds(width/2 - 150, height - 50, 130, 30);

        JButton addExpenseBtn = new JButton("Add expense");
        addExpenseBtn.setBounds(width/2 + 50, height - 50, 130, 30);

        viewExpensesBtn.addActionListener(e -> {
            switchToPage(EXPENSE_LIST_PAGE);
        });
        addExpenseBtn.addActionListener(e -> {
            switchToPage(ADD_EXPENSE_PAGE);
        });

        HOME_PAGE.add(viewExpensesBtn);
        HOME_PAGE.add(addExpenseBtn);
    }

    private static void updateExpenseListPage() {
        EXPENSE_LIST_CONTAINER.removeAll();

        int width = 800;

        /* list all expenses */
        int x = 0;
        for (Expense exp : user.getExpenses()) {
            JLabel noLabel = new JLabel(x + 1 + ".");
            noLabel.setBounds(10, x * 100, width, 80);
            JLabel incomeLabel = new JLabel("RM" + exp.getIncome().getSalary());
            incomeLabel.setBounds(80, x * 100, width, 80);
            JLabel incomeTypeLabel = new JLabel(exp.getIncome().getIncomeType());
            incomeTypeLabel.setBounds(220, x * 100, width, 80);
            JLabel expenseLabel = new JLabel("RM" + exp.getSpend());
            expenseLabel.setBounds(320, x * 100, width, 80);
            JLabel savingLabel = new JLabel("RM" + exp.getSaving().getAmount());
            savingLabel.setBounds(420, x * 100, width, 80);
            JLabel dateLabel = new JLabel(exp.getDate());
            dateLabel.setBounds(520, x * 100, width, 80);

            EXPENSE_LIST_CONTAINER.setPreferredSize(new Dimension(width, (x * 100)));
            EXPENSE_LIST_CONTAINER.add(noLabel);
            EXPENSE_LIST_CONTAINER.add(incomeLabel);
            EXPENSE_LIST_CONTAINER.add(incomeTypeLabel);
            EXPENSE_LIST_CONTAINER.add(expenseLabel);
            EXPENSE_LIST_CONTAINER.add(savingLabel);
            EXPENSE_LIST_CONTAINER.add(dateLabel);

            ++x;
        }
    }

    public static void show() {
        FRAME.setVisible(true);
    }

    public static void switchToPage(JPanel page) {
        if (page == EXPENSE_LIST_PAGE) {
            updateExpenseListPage();
        }

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

        switchToPage(START_PAGE);

        show();
    }
}
