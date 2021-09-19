package dev.gclopes.ControlExpenses.Services;

import dev.gclopes.ControlExpenses.model.MiscExpense;
import org.springframework.stereotype.Service;

@Service("MiscExpenseService")
public interface MiscExpenseService extends CrudService<MiscExpense, Long>{
    MiscExpense findByDesc(String desc);

}
