package dev.gclopes.ControlExpensesData.repositories;

import dev.gclopes.ControlExpensesData.model.TypeOfPayment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeOfPaymentRepository extends CrudRepository<TypeOfPayment, Long> {
    Optional<TypeOfPayment> findByName(String name);
}
