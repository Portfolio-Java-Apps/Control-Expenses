package dev.gclopes.ControlExpenses.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@ToString
@Getter
@Setter
public class Source extends BaseEntity {
    private String desc;
    @OneToMany(mappedBy = "source", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set <Movement> movements;

    public Source(long id, String desc) {
        this.setId(id);
        this.desc = desc;
    }


    public void setMovements(Set<Movement> movements) {
        this.movements = movements;
    }

    public Set<Movement> getMovements() {
        return movements;
    }
}
