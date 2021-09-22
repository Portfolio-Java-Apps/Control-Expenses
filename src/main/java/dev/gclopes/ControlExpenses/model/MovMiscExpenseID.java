package dev.gclopes.ControlExpenses.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class MovMiscExpenseID implements Serializable {
    private Long movementId;
    private Long miscExpenseId;


    public Long getMovementId() {
        return movementId;
    }

    public void setMovementId(Long movementId) {
        this.movementId = movementId;
    }

    public Long getMiscExpenseId() {
        return miscExpenseId;
    }

    public void setMiscExpenseId(Long miscExpenseId) {
        this.miscExpenseId = miscExpenseId;
    }
}
