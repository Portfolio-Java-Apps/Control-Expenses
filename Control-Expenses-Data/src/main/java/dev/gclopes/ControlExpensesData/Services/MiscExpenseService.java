package dev.gclopes.ControlExpensesData.Services;

import dev.gclopes.ControlExpensesData.model.MiscExpense;


public interface MiscExpenseService extends CrudService<MiscExpense, Long> {
    MiscExpense findByName(String name);

}
