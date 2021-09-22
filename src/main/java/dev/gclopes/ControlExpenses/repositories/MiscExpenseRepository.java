package dev.gclopes.ControlExpenses.repositories;

import dev.gclopes.ControlExpenses.model.MiscExpense;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MiscExpenseRepository extends CrudRepository<MiscExpense, Long> {
    Optional<MiscExpense> findByName(String desc);
}
