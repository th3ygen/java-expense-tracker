package models.incometype;

import models.Income;

public class Fulltime extends Income {

    public Fulltime(double salary) {
        super(salary);
    }

    @Override
    public String getIncomeType() {
        return "Full time";
    }
    
}
