package dev.gclopes.ControlExpenses.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class MovMiscExpenseID implements Serializable {
    @Column(name = "Movement_id")
    Long movementId;

    @Column(name = "MiscExpense_id")
    Long miscExpenseId;


}
