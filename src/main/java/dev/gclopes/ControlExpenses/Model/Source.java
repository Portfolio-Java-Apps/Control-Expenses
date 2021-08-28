package dev.gclopes.ControlExpenses.Model;


import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Source extends BaseEntity {
    private String desc;
    @OneToMany(mappedBy = "source")
    private Set <Movement> movements;

    @Override
    public String toString() {
        final StringBuilder sb;
        sb = new StringBuilder("Source{");
        sb.append("id=").append(id);
        sb.append(", desc='").append(desc).append('\'');
        sb.append(", movements=").append(movements);
        sb.append('}');
        return sb.toString();
    }
}
