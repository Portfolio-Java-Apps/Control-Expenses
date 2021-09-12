package dev.gclopes.ControlExpenses.Services;

import dev.gclopes.ControlExpenses.model.Source;

public interface SourceService extends CrudService<Source, Long> {
    Source findByDesc(String desc);
}
