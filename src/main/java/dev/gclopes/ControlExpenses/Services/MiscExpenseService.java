package dev.gclopes.ControlExpenses.Services;

import dev.gclopes.ControlExpenses.model.MiscExpense;

public interface MiscExpenseService extends CrudService<MiscExpense, Long>{
    MiscExpense findByDesc(String desc);

}
