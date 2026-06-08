package ir.chevil.service;

import ir.chevil.exception.ArticleNotFoundException;
import ir.chevil.exception.DuplicatedArticleException;
import ir.chevil.exception.InvalidUsernameException;
import ir.chevil.exception.WeakPasswordException;
import ir.chevil.model.Article;
import ir.chevil.enums.ArticleStatus;
import ir.chevil.model.user.Author;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AuthorService {
    private List<Author> authors;

    public AuthorService() {
        this.authors = new ArrayList<>();
    }

    public void registerAuthor(Author author) throws InvalidUsernameException, WeakPasswordException {
        if (author.getUsername().length() < 5) {
            throw new InvalidUsernameException("Error: Username length must be at least 5 characters.");
        }
        if (author.getPassword().length() < 8) {
            throw new WeakPasswordException("Error: Password length must be at least 8 characters.");
        }

        this.authors.add(author);
        IO.println("The author was successfully registered.");
    }

    public Author findAuthorById(int authorId) {
        return authors.stream()
                .filter(author -> author.getId() == authorId)
                .findFirst()
                .orElse(null);
    }

    public void registerArticleForAuthor(int authorId, Article article) throws DuplicatedArticleException {
        Author author = findAuthorById(authorId);
        if (author != null) {
            boolean exists = author.getArticles().stream()
                            .anyMatch(x -> x.getId() == article.getId());

            if (exists) {
                throw new DuplicatedArticleException("Error: An article with the ID " + article.getId() + " has already been registered for this author!");
            }

            author.addArticle(article);
            IO.println("The article was registered as a default (DRAFT) for the author.");
        } else {
            System.err.println("Author not found!");
        }
    }

    public void updateArticleStatus(int authorId, int articleId, ArticleStatus newStatus) throws ArticleNotFoundException {
        searchArticle(authorId, articleId).setStatus(newStatus);
        IO.println("Article status successfully changed to " + newStatus + ".");
    }

    public Article searchArticle(int authorId, int articleId) throws ArticleNotFoundException {
        Author author = findAuthorById(authorId);
        if (author == null) {
            throw new ArticleNotFoundException("Author not founded.");
        }

        return author.getArticles().stream()
                .filter(article -> article.getId() == articleId)
                .findFirst()
                .orElseThrow(() -> new ArticleNotFoundException("Error: No article with ID " + articleId + " was found for this author."));
    }

    public void showPublishedArticle() {
        IO.println("--- List of all published articles ---");
        long count = authors.stream()
                .flatMap(author -> author.getArticles().stream()
                        .filter(article -> article.getStatus() == ArticleStatus.PUBLISHED)
                        .map(article -> "Author: " + author.getFirstName() +
                                " " + author.getLastName() +
                                " -> " + article))
                .peek(IO::println)
                .count();
        if (count == 0) {
            IO.println("There are no published article.");
        }
    }

    public void showArticlesByAuthorId(int authorId) {
        Author author = findAuthorById(authorId);
        if (author != null) {
            Set<Article> articles = author.getArticles();
            if (articles.isEmpty()) IO.println("The author don't have any articles!");
            else {
                IO.println(
                        "--- Author's articles: " + author.getFirstName() + " " +
                        author.getLastName() + " ---"
                        );
                for (Article article : articles) {
                    IO.println(article);
                }
            }
        } else {
            System.err.println("Author not found.");;
        }
    }

    public void removeArticle(int authorId, int articleId) throws ArticleNotFoundException {
        findAuthorById(authorId)
                .getArticles().
                remove(searchArticle(authorId, articleId));
    }
}
