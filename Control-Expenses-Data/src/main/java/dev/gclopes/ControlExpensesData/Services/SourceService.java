package dev.gclopes.ControlExpensesData.Services;

import dev.gclopes.ControlExpensesData.model.Source;
import org.springframework.stereotype.Service;

@Service("SourceService")
public interface SourceService extends CrudService<Source, Long> {
    Source findByName(String name);
}
