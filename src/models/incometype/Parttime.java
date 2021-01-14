package models.incometype;

import models.Income;

public class Parttime extends Income {

    public Parttime(double salary) {
        super(salary);
    }

    @Override
    public String getIncomeType() {
        return "Part time";
    }
    
}
