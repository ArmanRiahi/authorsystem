package ir.chevil.controller.authorpage;

import ir.chevil.exception.ArticleNotFoundException;
import ir.chevil.service.ArticleService;

import java.util.Scanner;

public class RemoveArticle {
    public static void run(ArticleService service, Scanner in) {
        IO.println("--- Remove article ---");
        IO.print("Article title: ");
        String title = in.nextLine().trim();
        try {
            service.removeArticle(title);
        } catch (ArticleNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
