package dev.gclopes.ControlExpenses.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.Currency;

@Entity
@IdClass(MovPersonalGoodsID.class)
public class MovPersonalGoods {
    @Id
    private long idPersonalGoods;
    @Id
    private long idMovement;
    private Currency amount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovPersonalGoods that = (MovPersonalGoods) o;

        if (idPersonalGoods != that.idPersonalGoods) return false;
        return idMovement == that.idMovement;
    }

    @Override
    public int hashCode() {
        int result = (int) (idPersonalGoods ^ (idPersonalGoods >>> 32));
        result = 31 * result + (int) (idMovement ^ (idMovement >>> 32));
        return result;
    }

    @Override
    public String toString() {
        String sb;
        sb = "MovPersonalGoods{" + "idPersonalGoods=" + idPersonalGoods +
                ", idMovement=" + idMovement +
                ", amount=" + amount +
                '}';
        return sb;
    }
}
