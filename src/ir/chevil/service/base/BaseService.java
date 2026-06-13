package ir.chevil.service.base;

import ir.chevil.model.base.BaseModel;
import ir.chevil.repository.base.BaseRepository;

public interface BaseService<R extends BaseRepository<M, ID>, M extends BaseModel<ID>, ID> {
}
