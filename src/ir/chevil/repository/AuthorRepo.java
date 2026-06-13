package ir.chevil.repository;

import ir.chevil.model.user.Author;
import ir.chevil.repository.base.impl.BaseRepositoryImpl;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class AuthorRepo extends BaseRepositoryImpl<Author, UUID> {

    public AuthorRepo(List<Author> authors) {
        super(authors);
    }

    public Author findByUsername(String username) {
        return models.stream()
                .filter(x -> Objects.equals(x.getUsername(), username))
                .findFirst().orElse(null);
    }
}
