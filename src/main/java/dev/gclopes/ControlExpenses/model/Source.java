package dev.gclopes.ControlExpenses.model;


import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@ToString
public class Source extends BaseEntity {
    private String desc;

    @OneToMany(mappedBy = "source", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set <Movement> movements;

    public void setMovements(Set<Movement> movements) {
        this.movements = movements;
    }

    public Set<Movement> getMovements() {
        return movements;
    }


}
