package dev.gclopes.ControlExpenses.repositories;

import dev.gclopes.ControlExpenses.model.TypeOfPayment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeOfPaymentRepository extends CrudRepository<TypeOfPayment, Long> {
    Optional<TypeOfPayment> findByName(String name);
}
