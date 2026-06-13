package ir.chevil.repository.base.impl;

import ir.chevil.model.base.BaseModel;
import ir.chevil.repository.base.BaseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class BaseRepositoryImpl<M extends BaseModel<ID>, ID> implements BaseRepository<M, ID> {
    protected List<M> models;

    public BaseRepositoryImpl(List<M> models) {
        this.models = models;
    }

    @Override
    public void save(M model) {
        models.add(model);
    }

    @Override
    public Optional<M> findById(ID id) {
        return models.stream()
                .filter(x -> Objects.equals(x.getId(), id))
                .findFirst();
    }

    @Override
    public List<M> findAll() {
        return models;
    }

    @Override
    public void remove(M model) {
        models.remove(model);
    }
}
