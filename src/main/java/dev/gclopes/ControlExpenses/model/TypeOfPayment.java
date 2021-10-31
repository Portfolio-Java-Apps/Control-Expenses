package dev.gclopes.ControlExpenses.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name="TypeOfPayment")
@Table(name="TypeOfPayment")
@Setter
@Getter
@NoArgsConstructor
public class TypeOfPayment extends BaseEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Override
    public Long getId() {
        return super.getId();
    }
    public void SetId(Long id){
        this.setId(id);
    }

    public TypeOfPayment(Long id, String name) {
        this.setId(id);
        this.name = name;
    }

    @Column(name="name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Set<Movement> movements = new HashSet<>();

    @OneToMany(mappedBy = "typeOfPayment", cascade = CascadeType.ALL)
    public Set<Movement> getMovements() {
        return movements;
    }

    public void setMovements(Set<Movement> movements) {
        this.movements = movements;
    }


}
