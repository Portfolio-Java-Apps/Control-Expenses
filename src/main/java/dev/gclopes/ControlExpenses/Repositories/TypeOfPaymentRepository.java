package dev.gclopes.ControlExpenses.Repositories;

import dev.gclopes.ControlExpenses.Model.TypeOfPayment;
import org.springframework.data.repository.CrudRepository;

public interface TypeOfPaymentRepository extends CrudRepository<TypeOfPayment, Long> {
}
