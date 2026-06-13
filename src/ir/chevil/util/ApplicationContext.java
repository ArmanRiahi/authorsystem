package ir.chevil.util;

import ir.chevil.model.Article;
import ir.chevil.model.Category;
import ir.chevil.model.user.Author;
import ir.chevil.repository.ArticleRepo;
import ir.chevil.repository.AuthorRepo;

import java.util.ArrayList;
import java.util.List;

public class ApplicationContext {

    private static Author author;
    public static List<Author> authors;
    public static List<Article> articles;
    public static List<Category> categories;
    public static AuthorRepo authorRepo;
    public static ArticleRepo articleRepo;


    public static List<Author> getAuthors() {
        if (authors == null) {
            authors = new ArrayList<>(1000);
        }
        return authors;
    }

    public static List<Article> getArticles() {
        if (articles == null) {
            articles = new ArrayList<>();
        }
        return articles;
    }

    public static List<Category> getCategories() {
        if (categories == null) {
            categories = new ArrayList<>();
        }
        return categories;
    }

    public static AuthorRepo getAuthorRepo() {
        if (authorRepo == null) {
            authorRepo = new AuthorRepo(getAuthors());
        }
        return authorRepo;
    }

    public static ArticleRepo getArticleRepo() {
        if (articleRepo == null) {
            articleRepo = new ArticleRepo(getArticles());
        }
        return articleRepo;
    }

    public static void setAuthor(Author author) {
        ApplicationContext.author = author;
    }

    public static Author getAuthor() {
        return author;
    }
}