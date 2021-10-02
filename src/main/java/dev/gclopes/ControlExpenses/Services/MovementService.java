package dev.gclopes.ControlExpenses.Services;


import dev.gclopes.ControlExpenses.enumType.Type;
import dev.gclopes.ControlExpenses.model.Movement;

import java.util.Date;
import java.util.Set;

public interface MovementService extends CrudService<Movement, Long> {
    Set<Movement> findByType(Type type);
    Set<Movement> findByDate(Date date);
    Double getTotalAmountByDate(Date date);
    Double getTotalAmountByMonth(Integer month);
    Double getTotalAmountByYear(Integer year);
    Double getTotalDiscountByDate(Date date);
    Double getTotalDiscountByMonth(Integer month);
    Double getTotalDiscountByYear(Integer year);
}
