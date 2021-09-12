package dev.gclopes.ControlExpenses.repositories;

import dev.gclopes.ControlExpenses.model.MovMiscExpense;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovMiscExpenseRepository extends CrudRepository<MovMiscExpense, Long> {
}
