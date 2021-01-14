package models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Expense {
    private double spend;
    private String date;

    public Expense(double spend) {
        this.spend = spend;
        
        /* date as string */
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        this.date = df.format(Calendar.getInstance().getTime());
    }

    public double getSpend() {
        return spend;
    }

    public String getDate() {
        return date;
    }

    public void setSpend(double spend) {
        this.spend = spend;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
