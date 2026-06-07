package ir.chevil.service;

import ir.chevil.exception.ArticleNotFoundException;
import ir.chevil.exception.InvalidUsernameException;
import ir.chevil.exception.WeakPasswordException;
import ir.chevil.model.Article;
import ir.chevil.model.ArticleStatus;
import ir.chevil.model.Author;

import java.util.ArrayList;
import java.util.List;

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
        for (Author author : authors) {
            if (author.getId() == authorId) return author;
        }

        return null;
    }

    public void registerArticleForAuthor(int authorId, Article article) {
        Author author = findAuthorById(authorId);
        if (author != null) {
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

        for (Article article : author.getArticles()) {
            if (article.getId() == articleId) return article;
        }
        throw new ArticleNotFoundException("Error: No article with ID " + articleId + " was found for this author.");
    }

    public void showPublishedArticle() {
        IO.println("--- List of all published articles ---");
        boolean hasPublished = false;
        for (Author author : authors) {
            for (Article article : author.getArticles()) {
                if (article.getStatus() == ArticleStatus.PUBLISHED) {
                    IO.println(
                            "Author: " + author.getFirstName() + " " + author.getLastName() +
                                    " -> " + article
                    );
                    hasPublished = true;
                }
            }
        }

        if (!hasPublished) {
            System.err.println("There are no published article.");
        }
    }

    public void showArticlesByAuthorId(int authorId) {
        Author author = findAuthorById(authorId);
        if (author != null) {
            List<Article> articles = author.getArticles();
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
