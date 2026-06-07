package ir.chevil.model;

import java.util.Objects;

public class Article {
    private int id;
    private String title;
    private String content;
    private Category category;

    public Article(int id, String title, String content, Category category) {
        this.setId(id);
        this.setTitle(title);
        this.setContent(content);
        this.setCategory(category);
    }

    public Article(int id, String title, String content) {
        this(id, title, content, null);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Article article = (Article) obj;
        return Objects.equals(id, article.id);
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ",title=" + title +
                ",content=" + content +
                ",category=" + (category != null ? category.getTitle() : "No Category") +
                '}';
    }
}
