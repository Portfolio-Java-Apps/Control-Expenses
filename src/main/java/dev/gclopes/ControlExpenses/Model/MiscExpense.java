package dev.gclopes.ControlExpenses.Model;

import javax.persistence.Entity;


@Entity
public class MiscExpense extends BaseEntity {
    private String desc;

    @Override
    public String toString() {
        final StringBuilder sb;
        sb = new StringBuilder("MiscExpense{");
        sb.append("id=").append(id);
        sb.append(", desc='").append(desc).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
