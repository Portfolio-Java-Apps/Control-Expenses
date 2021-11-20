package dev.gclopes.ControlExpensesData.Services;

import javax.transaction.NotSupportedException;
import java.util.Set;

public interface CrudService<T, ID>{
    Set<T> findAll();

    T findById(ID id) throws NotSupportedException;

    T save(T object);

    void delete(T object);

    void deleteById(ID id) throws Exception;
}
