package ir.chevil.service;

import ir.chevil.model.Article;
import ir.chevil.model.Author;

import java.util.ArrayList;
import java.util.List;

public class WritingSystem {
    private List<Author> authors;

    public WritingSystem() {
        this.authors = new ArrayList<>();
    }

    public void registerAuthor(Author author) {
        this.authors.add(author);
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
            IO.println("Done!");
        } else {
            System.err.println("Author not found!");
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
}
