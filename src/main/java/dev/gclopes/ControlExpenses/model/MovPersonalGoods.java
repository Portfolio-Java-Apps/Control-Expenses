package dev.gclopes.ControlExpenses.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Currency;

@Entity(name = "MovPersonalGoods")
@Table(name = "MovPersonalGoods")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovPersonalGoods {
    @EmbeddedId
    private MovPersonalGoodsID id;

    @ManyToOne
    @MapsId("movementId")
    @JoinColumn(name = "Movement_id")
    private Movement movement;

    @ManyToOne
    @MapsId("personalGoodId")
    @JoinColumn(name = "PersonalGood_id")
    private PersonalGoods personalGoods;


    @Column(name = "Amount")
    private Currency amount;



}
