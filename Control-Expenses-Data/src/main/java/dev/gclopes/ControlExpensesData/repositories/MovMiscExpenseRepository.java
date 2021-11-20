package dev.gclopes.ControlExpensesData.repositories;

import dev.gclopes.ControlExpensesData.model.MovMiscExpense;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MovMiscExpenseRepository extends CrudRepository<MovMiscExpense, Long> {
    Set<MovMiscExpense> findAllByMovementId(Long id);
    Set<MovMiscExpense> findAllByMiscExpenseId(Long id);
    @Query(value="SELECT SUM(amount) FROM mov_misc_expense  WHERE movement_id = :id", nativeQuery = true)
    Double getTotalAmountByMovement(@Param("id") Long id);
    @Query(value="SELECT SUM(amount) FROM mov_misc_expense  WHERE m.misc_expense_id = :id", nativeQuery = true)
    Double getTotalAmountByMiscExpense(@Param("id") Long id);

    void deleteByMovementIdAndMiscExpenseId(Long movementId, Long miscExpenseId);
}
