package models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Expense {
    private double spend;
    private String date;

    private Category category;
    private Income income;
    private Saving saving;

    public Expense(double spend, Category category) {
        this.spend = spend;
        this.category = category;
        
        /* date as string */
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        this.date = df.format(Calendar.getInstance().getTime());
    }

    public double getSpend() {
        return spend;
    }

    public Category getCategory() {
        return category;
    }

    public Income getIncome() {
        return income;
    }

    public Saving getSaving() {
        return saving;
    }

    public String getDate() {
        return date;
    }

    public void setSpend(double spend) {
        this.spend = spend;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setIncome(Income income) {
        this.income = income;
    }

    public void setSaving(Saving saving) {
        this.saving = saving;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
