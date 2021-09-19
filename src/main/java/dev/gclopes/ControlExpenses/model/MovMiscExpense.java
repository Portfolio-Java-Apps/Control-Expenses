package dev.gclopes.ControlExpenses.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Currency;

@Entity(name = "MovMiscExpense")
@Table(name = "MovMiscExpense")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovMiscExpense {
    @EmbeddedId
    private MovMiscExpenseID id;

    @ManyToOne
    @JoinColumn(name = "id")
    private Movement movement;

    @ManyToOne
    @JoinColumn(name = "id")
    private MiscExpense miscExpense;


    @Column(name = "Amount")
    private Currency amount;

}
