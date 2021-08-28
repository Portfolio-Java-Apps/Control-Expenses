package dev.gclopes.ControlExpenses.Model;



import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Source extends BaseEntity {
    private String desc;
    @OneToMany(mappedBy = "source")
    private Set <Movement> movements;
}
