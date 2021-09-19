package dev.gclopes.ControlExpenses.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity(name="Source")
@Table(name="Source")
@ToString
@Getter
@Setter
public class Source extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Override
    public Long getId() {
        return super.getId();
    }
    public void SetId(Long id){
        this.setId(id);
    }
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
