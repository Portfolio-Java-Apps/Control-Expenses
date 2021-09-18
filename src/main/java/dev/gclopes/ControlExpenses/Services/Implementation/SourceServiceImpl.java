package dev.gclopes.ControlExpenses.Services.Implementation;

import dev.gclopes.ControlExpenses.Services.SourceService;
import dev.gclopes.ControlExpenses.model.Source;
import dev.gclopes.ControlExpenses.repositories.SourceRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
public class SourceServiceImpl implements SourceService {
    private final SourceRepository sourceRepository;

    public SourceServiceImpl(SourceRepository sourceRepository) {
        this.sourceRepository = sourceRepository;
    }

    @Override
    public Set<Source> findAll() {
        Set<Source> sources = new HashSet<>();
        sourceRepository.findAll().forEach(sources::add);
        return sources;
    }

    @Override
    public Source findById(Long aLong) {
        return sourceRepository.findById(aLong).orElse(null);
    }

    @Override
    public Source save(Source object) {
        return sourceRepository.save(object);
    }

    @Override
    public void delete(Source object) {
        sourceRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        sourceRepository.deleteById(aLong);
    }

    @Override
    public Source findByDesc(String desc) {
        return sourceRepository.findByDesc(desc).orElse(null);
    }
}
