package ir.chevil.controller.authorpage;

import ir.chevil.enums.ArticleStatus;
import ir.chevil.exception.ArticleNotFoundException;
import ir.chevil.model.Article;
import ir.chevil.service.ArticleService;

import java.util.Scanner;

public class ChangeArticleStatus {
    public static void run(ArticleService service, Scanner in) {
        IO.println("--- Change article status ---");
        IO.print("Article title: ");
        String title = in.nextLine().trim();

        try {
            Article article = service.findArticleByTitle(title);

            IO.println("1) DRAFT  2) REVIEWING  3) PUBLISHED  4) REJECTED");
            IO.print("Choose new status: ");
            int statusChoice = in.nextInt();
            in.nextLine();

            ArticleStatus status = ArticleStatus.DRAFT;
            if (statusChoice == 2) status = ArticleStatus.REVIEWING;
            else if (statusChoice == 3) status = ArticleStatus.PUBLISHED;
            else if (statusChoice == 4) status = ArticleStatus.REJECTED;

            service.updateArticleStatus(article.getId().toString(), status);
        } catch (ArticleNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
