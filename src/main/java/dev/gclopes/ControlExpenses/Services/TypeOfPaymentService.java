package dev.gclopes.ControlExpenses.Services;

import dev.gclopes.ControlExpenses.model.TypeOfPayment;


public interface TypeOfPaymentService extends CrudService<TypeOfPayment, Long> {
    TypeOfPayment findByName(String name);
}
