package ir.chevil.controller.authorpage;

import ir.chevil.exception.DuplicatedArticleException;
import ir.chevil.model.Article;
import ir.chevil.model.Category;
import ir.chevil.model.user.Author;
import ir.chevil.service.ArticleService;

import java.util.Scanner;

public class RegisterArticle {
    public static void run(ArticleService service, Scanner in, Author author,
                           Category art, Category tec, Category other) {
        IO.println("--- Register new article ---");
        IO.print("Article title: ");
        String title = in.nextLine().trim();
        IO.print("Article content: ");
        String content = in.nextLine();
        IO.println("Chose a category: 1) art 2) tec 3) other 4) non-category");
        IO.print("> ");
        String choice = in.nextLine().trim();

        Category selected = switch (choice) {
            case "1" -> art;
            case "2" -> tec;
            case "3" -> other;
            default -> null;
        };

        try {
            Article article = new Article(title, content, selected);
            service.registerArticleForAuthor(article);
            author.addArticle(article);
        } catch (DuplicatedArticleException e) {
            System.err.println(e.getMessage());
        }
    }
}
