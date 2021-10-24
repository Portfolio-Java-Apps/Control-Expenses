package dev.gclopes.ControlExpenses.Services;

import dev.gclopes.ControlExpenses.model.MovMiscExpense;

import java.util.Set;

public interface MovMiscExpenseService extends CrudService<MovMiscExpense, Long> {
    Set<MovMiscExpense> findAllByMovementId(Long id);
    Set<MovMiscExpense> findAllByMiscExpenseId(Long id);
    Double getTotalAmountByMovement(Long id);
    Double getTotalAmountByMiscExpense(Long id);
}
