package dev.gclopes.ControlExpenses.Model;

import javax.persistence.*;
import java.util.Currency;

@Entity
@IdClass(MovPersonalGoodsID.class)
public class MovPersonalGoods {
    @Id
    private long idPersonalGoods;
    @Id
    private long idMovement;
    private Currency amount;
}
