package ir.chevil.service;

import ir.chevil.exception.*;
import ir.chevil.model.Article;
import ir.chevil.enums.ArticleStatus;
import ir.chevil.model.user.Author;
import ir.chevil.repository.AuthorRepo;
import ir.chevil.service.base.impl.BaseServiceImpl;

import java.util.*;

public class AuthorService extends BaseServiceImpl<AuthorRepo, Author, UUID> {

    public AuthorService(AuthorRepo authorRepo, List<Author> authors) {
        super(authorRepo, authors);
    }

    public void registerAuthor(Author author) throws DuplicatedAuthorException, InvalidUsernameException, WeakPasswordException {
        if (repository.findByUsername(author.getUsername()) != null) {
            throw new DuplicatedAuthorException("Error:  Username is already in use.");
        }
        if (author.getUsername().length() < 5) {
            throw new InvalidUsernameException("Error: Username length must be at least 5 characters.");
        }
        if (author.getPassword().length() < 8) {
            throw new WeakPasswordException("Error: Password length must be at least 8 characters.");
        }

        repository.save(author);
        IO.println("The author was successfully registered.");
    }

    public Author findAuthorByUsername(String username) throws UsernameNotFoundException {
        if (repository.findByUsername(username) == null) {
            throw new UsernameNotFoundException("Error:  Username not found.");
        }

        return repository.findByUsername(username);
    }

    public boolean checkUsernameAndPassword(Author author, String username, String password) {
        return author.getUsername().equals(username) && author.getPassword().equals(password);
    }

//    public void showArticlesByAuthorId(int authorId) {
//        Author author = findAuthorById(authorId);
//        if (author != null) {
//            Set<Article> articles = author.getArticles();
//            if (articles.isEmpty()) IO.println("The author don't have any articles!");
//            else {
//                IO.println(
//                        "--- Author's articles: " + author.getFirstName() + " " +
//                        author.getLastName() + " ---"
//                        );
//                for (Article article : articles) {
//                    IO.println(article);
//                }
//            }
//        } else {
//            System.err.println("Author not found.");;
//        }
//    }
}
