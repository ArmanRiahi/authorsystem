package ir.chevil.service;

import ir.chevil.enums.ArticleStatus;
import ir.chevil.exception.ArticleNotFoundException;
import ir.chevil.exception.DuplicatedArticleException;
import ir.chevil.model.Article;
import ir.chevil.model.user.Author;
import ir.chevil.repository.ArticleRepo;
import ir.chevil.service.base.impl.BaseServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ArticleService extends BaseServiceImpl<ArticleRepo, Article, UUID> {
    public ArticleService(ArticleRepo articleRepo, List<Article> articles) {
        super(articleRepo, articles);
    }

    public void registerArticleForAuthor(Article article) throws DuplicatedArticleException {
        if (repository.findByTitle(article.getTitle()) != null) {
            throw new DuplicatedArticleException("Article with this title already exists");
        }
        repository.save(article);
        IO.println("The article was registered as a default (DRAFT) for the author.");
    }

    public void updateArticleStatus(String articleId, ArticleStatus newStatus) {
        repository.updateArticleStatus(articleId, newStatus);
        IO.println("Article status successfully changed to " + newStatus + ".");
    }

    public Article findArticleByTitle(String title) throws ArticleNotFoundException {
        if (repository.findByTitle(title) == null) {
            throw new ArticleNotFoundException("Error: Article with title " + title + " not found.");
        }

        return repository.findByTitle(title);
    }

    public List<Article> findPublishedArticle() {
        return repository.findPublishedArticles();
    }

    public void removeArticle(String title) throws ArticleNotFoundException {
        repository.remove(findArticleByTitle(title));
    }

    public List<Article> findAllArticles() {
        return repository.findAll();
    }
}
