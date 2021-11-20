package dev.gclopes.ControlExpensesData.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "MovMiscExpense")
@Table(name = "MovMiscExpense")
@AllArgsConstructor
@NoArgsConstructor
public class MovMiscExpense {
    @EmbeddedId
    private MovMiscExpenseID movMiscExpenseID;
    public MovMiscExpense(MovMiscExpenseID movMiscExpenseID)
    {
        this.movMiscExpenseID = movMiscExpenseID;
    }
    public MovMiscExpense(Long movementId, Long miscExpenseId)
    {
        movMiscExpenseID = new MovMiscExpenseID(movementId, miscExpenseId);
    }

    @ManyToOne
    @MapsId("movementId")
    @JoinColumn(name = "movement_id")
    private Movement movement;

    @ManyToOne
    @MapsId("miscExpenseId")
    @JoinColumn(name = "miscExpense_id")
    private MiscExpense miscExpense;


    @Column(name = "Amount")
    private Double amount;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public MovMiscExpenseID getMovMiscExpenseID() {
        return movMiscExpenseID;
    }

    public void setMovMiscExpenseID(MovMiscExpenseID movMiscExpenseID) {
        this.movMiscExpenseID = movMiscExpenseID;
    }

    public Movement getMovement() {
        return movement;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }

    public MiscExpense getMiscExpense() {
        return miscExpense;
    }

    public void setMiscExpense(MiscExpense miscExpense) {
        this.miscExpense = miscExpense;
    }

    @Override
    public String toString() {
        return "MovMiscExpense{" + "movMiscExpenseID=" + movMiscExpenseID +
                ", movement=" + movement +
                ", miscExpense=" + miscExpense +
                ", amount=" + String.format("%.02f",amount) +
                '}';
    }
}
