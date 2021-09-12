package dev.gclopes.ControlExpenses.model;

import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Getter
public class BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseEntity that = (BaseEntity) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

}
