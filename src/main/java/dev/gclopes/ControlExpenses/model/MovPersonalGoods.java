package dev.gclopes.ControlExpenses.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "MovPersonalGoods")
@Table(name = "MovPersonalGoods")
@AllArgsConstructor
@NoArgsConstructor
public class MovPersonalGoods {
    @EmbeddedId
    private MovPersonalGoodsID movPersonalGoodsID;
    public MovPersonalGoods(MovPersonalGoodsID movPersonalGoodsID)
    {
        this.movPersonalGoodsID = movPersonalGoodsID;
    }
    public MovPersonalGoods(Long movementId, Long personalGoodsID)
    {
        movPersonalGoodsID = new MovPersonalGoodsID(movementId, personalGoodsID);
    }
    @ManyToOne
    @MapsId("movementId")
    @JoinColumn(name = "movement_id")
    private Movement movement;

    @ManyToOne
    @MapsId("personalGoodsId")
    @JoinColumn(name = "PersonalGoods_id")
    private PersonalGoods personalGoods;

    @Column(name = "Amount")
    private Double amount;



    public MovPersonalGoodsID getMovPersonalGoodsID() {
        return movPersonalGoodsID;
    }

    public void setMovPersonalGoodsID(MovPersonalGoodsID movPersonalGoodsID) {
        this.movPersonalGoodsID = movPersonalGoodsID;
    }

    public Movement getMovement() {
        return movement;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }

    public PersonalGoods getPersonalGoods() {
        return personalGoods;
    }

    public void setPersonalGoods(PersonalGoods personalGoods) {
        this.personalGoods = personalGoods;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "MovPersonalGoods{" + "movPersonalGoodsID=" + movPersonalGoodsID +
                ", movement=" + movement +
                ", personalGoods=" + personalGoods +
                ", amount=" + amount +
                '}';
    }
}
