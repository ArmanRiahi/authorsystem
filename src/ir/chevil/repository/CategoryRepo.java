package ir.chevil.repository;

import ir.chevil.model.Category;
import ir.chevil.repository.base.impl.BaseRepositoryImpl;

import java.util.List;
import java.util.UUID;

public class CategoryRepo extends BaseRepositoryImpl<Category, UUID> {
    public CategoryRepo(List<Category> categories) {
        super(categories);
    }
}
