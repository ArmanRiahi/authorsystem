package ir.chevil.model;

import ir.chevil.enums.ArticleStatus;
import ir.chevil.model.base.BaseModel;

import java.util.Objects;
import java.util.UUID;

public class Article extends BaseModel<UUID> {
    private String title;
    private String content;
    private Category category;
    private ArticleStatus status;

    public Article(String title, String content, Category category) {
        this.setId(UUID.randomUUID());
        this.setTitle(title);
        this.setContent(content);
        this.setCategory(category);
        this.setStatus(ArticleStatus.DRAFT);
    }

    public Article(String title, String content) {
        this(title, content, null);
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

    public ArticleStatus getStatus() {
        return status;
    }

    public void setStatus(ArticleStatus status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Article article = (Article) obj;
        return Objects.equals(this.getId(), article.getId());
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + this.getId() +
                ",title=" + title +
                ",content=" + content +
                ",category=" + (category != null ? category.getTitle() : "No Category") +
                '}';
    }
}
