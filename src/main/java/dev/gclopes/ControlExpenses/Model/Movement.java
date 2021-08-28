package dev.gclopes.ControlExpenses.Model;

import dev.gclopes.ControlExpenses.Enum.Type;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Currency;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Movement extends BaseEntity{
    private Type type;

    @ManyToOne(fetch = FetchType.LAZY)
    private Source source;

    private Date date;

    private Currency totalAmount;
    private Currency discount;
    @ManyToOne(fetch=FetchType.LAZY)
    private TypeOfPayment typeOfPayment;

    @Override
    public String toString() {
        return "Movement{" + "id=" + id +
                ", type=" + type +
                ", source=" + source +
                ", date=" + date +
                ", totalAmount=" + totalAmount +
                ", discount=" + discount +
                ", typeOfPayment=" + typeOfPayment +
                '}';
    }
}
