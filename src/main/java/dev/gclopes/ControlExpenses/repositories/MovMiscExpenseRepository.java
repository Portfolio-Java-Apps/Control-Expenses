package dev.gclopes.ControlExpenses.repositories;

import dev.gclopes.ControlExpenses.model.MovMiscExpense;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MovMiscExpenseRepository extends CrudRepository<MovMiscExpense, Long> {
    Set<MovMiscExpense> findAllByIdmovementId(Long id);
    Set<MovMiscExpense> findAllByIdmiscExpenseId(Long id);
    @Query(value="SELECT SUM(m.Amount) FROM mov_misc_expense m WHERE m.misc_expense_id = :id")
    Double getTotalAmountByMovement(@Param("id") Long id);
    @Query(value="SELECT SUM(m.Amount) FROM mov_misc_expense m WHERE m.movement_id = :id")
    Double getTotalAmountByMiscExpense(@Param("id") Long id);
}
