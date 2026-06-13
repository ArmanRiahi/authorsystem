package ir.chevil.model.user;


import ir.chevil.model.Article;
import ir.chevil.model.user.base.User;

import java.util.HashSet;
import java.util.Set;

public class Author extends User {
    private Set<Article> articles;

    public Author(
            String firstName,
            String lastName,
            String username,
            String password
    ) {
        super(firstName, lastName, username, password);
        this.setArticles(new HashSet<>());
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    public void addArticle(Article article) {this.articles.add(article);}

    @Override
    public String toString() {
        return "Author{" +
                "id=" + this.getId() +
                "name=" + this.getFirstName() + " " + this.getLastName() +
                "username=" + this.getUsername() +
                "articlesCount=" + articles.size() +
                '}';
    }
}
