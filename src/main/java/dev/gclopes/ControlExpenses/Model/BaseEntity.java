package dev.gclopes.ControlExpenses.Model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseEntity that = (BaseEntity) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

}
