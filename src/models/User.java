package models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    
    /* using List as array, for dynamic size compatibility */
    private List<Expense> expenses;

    public User(String username) {
        this.username = username;

        /* create list: Expense */
        this.expenses = new ArrayList<Expense>();
    }

    public String getUsername() {
        return username;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public void addExpense(Expense expense) {
        this.expenses.add(expense);
    }
}
