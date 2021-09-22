package dev.gclopes.ControlExpenses.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class MovPersonalGoodsID implements Serializable {

    private Long movementId;
    private Long personalGoodId;


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


}
