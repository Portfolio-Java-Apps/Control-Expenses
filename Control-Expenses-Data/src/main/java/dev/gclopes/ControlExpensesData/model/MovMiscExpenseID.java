package dev.gclopes.ControlExpensesData.model;

import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
public class MovMiscExpenseID implements Serializable {
    private Long movementId;
    private Long miscExpenseId;

    public MovMiscExpenseID(Long movementId, Long miscExpenseId)
    {
        this.movementId = movementId;
        this.miscExpenseId = miscExpenseId;
    }

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

    @Override
    public String toString() {
        return "MovMiscExpenseID{" + "movementId=" + movementId +
                ", miscExpenseId=" + miscExpenseId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovMiscExpenseID that = (MovMiscExpenseID) o;

        if (!movementId.equals(that.movementId)) return false;
        return miscExpenseId.equals(that.miscExpenseId);
    }

    @Override
    public int hashCode() {
        int result = movementId.hashCode();
        result = 31 * result + miscExpenseId.hashCode();
        return result;
    }
}
