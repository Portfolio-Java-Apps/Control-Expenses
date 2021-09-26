package dev.gclopes.ControlExpenses.Services;

import dev.gclopes.ControlExpenses.model.Source;
import org.springframework.stereotype.Service;

@Service("SourceService")
public interface SourceService extends CrudService<Source, Long> {
    Source findByName(String name);
}
