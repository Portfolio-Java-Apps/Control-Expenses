package dev.gclopes.ControlExpenses.Model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class TypeOfPayment extends BaseEntity{
    private String desc;
    @OneToMany(mappedBy = "typeOfPayment")
    Set<Movement> movements;
}
