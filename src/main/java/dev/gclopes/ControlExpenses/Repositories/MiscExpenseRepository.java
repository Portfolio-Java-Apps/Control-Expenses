package dev.gclopes.ControlExpenses.Repositories;

import dev.gclopes.ControlExpenses.Model.MiscExpense;
import org.springframework.data.repository.CrudRepository;

public interface MiscExpenseRepository extends CrudRepository<MiscExpense, Long> {
}
