package dev.gclopes.ControlExpenses.Model;

import javax.persistence.*;
import java.util.Currency;

@Entity
@IdClass(MovMisExpenseID.class)
public class MovMiscExpense {
    @Id
    private long idMovement;
    @Id
    private long idMiscExpense;
    private Currency amount;
}
