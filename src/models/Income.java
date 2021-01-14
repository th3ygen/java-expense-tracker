package models;

public abstract class Income {
    protected double salary;
    
    public Income(double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return this.salary;
    }
    
    public void setSalary(double salary) {
        this.salary = salary;
    }

    public abstract String getIncomeType();
}
