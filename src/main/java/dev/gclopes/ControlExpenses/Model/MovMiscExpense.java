package dev.gclopes.ControlExpenses.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.Currency;

@Entity
@IdClass(MovMisExpenseID.class)
public class MovMiscExpense {
    @Id
    private long idMovement;
    @Id
    private long idMiscExpense;
    private Currency amount;

    @Override
    public String toString() {
        return "MovMiscExpense{" + "idMovement=" + idMovement +
                ", idMiscExpense=" + idMiscExpense +
                ", amount=" + amount +
                '}';
    }
}
