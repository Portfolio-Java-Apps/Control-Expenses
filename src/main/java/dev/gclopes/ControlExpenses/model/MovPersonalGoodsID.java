package dev.gclopes.ControlExpenses.model;

import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
public class MovPersonalGoodsID implements Serializable {

    private Long movementId;
    private Long personalGoodId;
    public MovPersonalGoodsID(Long movementId, Long personalGoodId)
    {
        this.movementId = movementId;
        this.personalGoodId = personalGoodId;
    }

    public Long getMovementId() {
        return movementId;
    }

    public void setMovementId(Long movementId) {
        this.movementId = movementId;
    }

    public Long getPersonalGoodId() {
        return personalGoodId;
    }

    public void setPersonalGoodId(Long personalGoodId) {
        this.personalGoodId = personalGoodId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovPersonalGoodsID that = (MovPersonalGoodsID) o;

        if (!Objects.equals(movementId, that.movementId)) return false;
        return Objects.equals(personalGoodId, that.personalGoodId);
    }

    @Override
    public int hashCode() {
        int result = movementId != null ? movementId.hashCode() : 0;
        result = 31 * result + (personalGoodId != null ? personalGoodId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MovPersonalGoodsID{" + "movementId=" + movementId +
                ", personalGoodId=" + personalGoodId +
                '}';
    }
}
