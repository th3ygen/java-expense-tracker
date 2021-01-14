package models;

public class Saving {
    private double amount;

    public Saving(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void addToSaving(double amount) {
        this.amount += amount;
    }
}
