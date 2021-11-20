package dev.gclopes.ControlExpensesData.Services;

import dev.gclopes.ControlExpensesData.model.TypeOfPayment;


public interface TypeOfPaymentService extends CrudService<TypeOfPayment, Long> {
    TypeOfPayment findByName(String name);
}
