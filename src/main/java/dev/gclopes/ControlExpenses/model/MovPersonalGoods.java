package dev.gclopes.ControlExpenses.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Currency;
import java.util.Objects;

@Entity(name = "MovPersonalGoods")
@Table(name = "MovPersonalGoods")
@AllArgsConstructor
@NoArgsConstructor
public class MovPersonalGoods {
    @EmbeddedId
    private MovPersonalGoodsID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("miscExpenseId")
    private PersonalGoods personalGoods;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("movementId")
    private Movement movement;
    @Column(name = "Amount")
    private Currency amount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovPersonalGoods that = (MovPersonalGoods) o;

        return Objects.equals(movement, that.movement) &&
                Objects.equals(personalGoods, that.personalGoods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movement, personalGoods);
    }

}
