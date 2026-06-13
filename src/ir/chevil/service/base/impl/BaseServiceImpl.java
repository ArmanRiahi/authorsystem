package ir.chevil.service.base.impl;

import ir.chevil.model.base.BaseModel;
import ir.chevil.repository.base.BaseRepository;
import ir.chevil.service.base.BaseService;

import java.util.List;

public class BaseServiceImpl<R extends BaseRepository<M, ID>, M extends BaseModel<ID>, ID> implements BaseService <R, M, ID> {
    protected R repository;
    protected List<M> models;

    public BaseServiceImpl(R repository, List<M> models) {
        this.repository = repository;
        this.models = models;
    }
}
