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
public class MovPersonalGoodsID implements Serializable {
    @Column(name = "PersonalGoodsId")
    private long personalGoodsId;

    @Column(name = "MovementId")
    private long movementId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovPersonalGoodsID that = (MovPersonalGoodsID) o;

        return Objects.equals(personalGoodsId, that.personalGoodsId) &&
                Objects.equals(movementId, that.movementId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personalGoodsId, movementId);
    }
}
