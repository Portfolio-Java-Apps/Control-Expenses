package dev.gclopes.ControlExpenses.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class MovMiscExpenseID implements Serializable {
    @Column(name = "MiscExpenseId")
    private long miscExpenseId;

    @Column(name = "MovementId")
    private long movementId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        MovMiscExpenseID that = (MovMiscExpenseID) o;
        return Objects.equals(miscExpenseId, that.miscExpenseId) &&
                Objects.equals(movementId, that.movementId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(miscExpenseId, movementId);
    }
}
