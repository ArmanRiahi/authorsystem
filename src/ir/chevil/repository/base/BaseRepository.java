package ir.chevil.repository.base;

import ir.chevil.model.base.BaseModel;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<M extends BaseModel<ID>, ID> {
    void save(M model);
    Optional<M> findById(ID id);
    List<M> findAll();
    void remove(M model);
}
