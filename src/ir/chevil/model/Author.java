package ir.chevil.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Author {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private List<Article> articles;

    public Author(
            int id,
            String lastName,
            String firstName,
            String username,
            String password
    ) {
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setUsername(username);
        this.setPassword(password);
        this.setArticles(new ArrayList<>());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public void addArticle(Article article) {this.articles.add(article);}

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Author author = (Author) obj;
        return Objects.equals(author.id, id);
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                "name=" + firstName + " " + lastName +
                "username=" + username +
                "articlesCount=" + articles.size() +
                '}';
    }
}
