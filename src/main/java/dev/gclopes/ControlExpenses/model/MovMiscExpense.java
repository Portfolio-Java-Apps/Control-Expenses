package dev.gclopes.ControlExpenses.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Currency;
import java.util.Objects;

@Entity(name = "MovMiscExpense")
@Table(name = "MovMiscExpense")
@AllArgsConstructor
@NoArgsConstructor
public class MovMiscExpense {
    @EmbeddedId
    private MovMiscExpenseID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("miscExpenseId")
    private MiscExpense miscExpense;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("movementId")
    private Movement movement;

    @Column(name = "Amount")
    private Currency amount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        MovMiscExpense that = (MovMiscExpense) o;
        return Objects.equals(movement, that.movement) &&
                Objects.equals(miscExpense, that.miscExpense);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movement, miscExpense);
    }


}
