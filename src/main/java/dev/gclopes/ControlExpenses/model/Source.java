package dev.gclopes.ControlExpenses.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity(name="Source")
@Table(name="Source")

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

    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Set <Movement> movements;

    public Source(long id, String name) {
        this.setId(id);
        this.name = name;
    }


    @OneToMany(mappedBy = "source", cascade = CascadeType.ALL)
    @Column(name="movement_id")
    public Set<Movement> getMovements() {
        return movements;
    }

    public void setMovements(Set<Movement> movements) {
        this.movements = movements;
    }
}
