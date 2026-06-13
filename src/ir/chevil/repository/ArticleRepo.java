package ir.chevil.repository;

import ir.chevil.enums.ArticleStatus;
import ir.chevil.model.Article;
import ir.chevil.repository.base.impl.BaseRepositoryImpl;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ArticleRepo extends BaseRepositoryImpl<Article, UUID> {
    public ArticleRepo(List<Article> articles) {
        super(articles);
    }

    public void updateArticleStatus(String articleId, ArticleStatus status) {
        this.findById(UUID.fromString(articleId))
                .ifPresent(article -> article.setStatus(status));
    }

    public Article findByTitle(String title) {
        return models.stream()
                .filter(article -> Objects.equals(article.getTitle(), title))
                .findFirst().orElse(null);
    }

    public List<Article> findPublishedArticles() {
        return models.stream()
                .filter(article -> Objects.equals(article.getStatus(), ArticleStatus.PUBLISHED))
                .toList();
    }
}
