package ir.chevil.controller.authorpage;

import ir.chevil.exception.ArticleNotFoundException;
import ir.chevil.model.Article;
import ir.chevil.service.ArticleService;

import java.util.Scanner;

public class ArticleSearch {
    public static void run(ArticleService service, Scanner in) {
        IO.println("--- Article search ---");
        IO.print("Article title: ");
        String title = in.nextLine().trim();
        try {
            Article found = service.findArticleByTitle(title);
            IO.println("Article found:\n" + found);
        } catch (ArticleNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
