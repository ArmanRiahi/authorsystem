package ir.chevil.controller;

import ir.chevil.exception.ArticleNotFoundException;
import ir.chevil.service.AuthorService;

import java.util.Scanner;

public class RemoveArticle {
    public static void run(AuthorService service, Scanner in) {
        IO.println("--- Remove article ---");
        IO.print("Author ID: ");
        int authorId = in.nextInt();
        IO.print("Article ID: ");
        int articleId = in.nextInt();
        in.nextLine();
        try {
            service.removeArticle(authorId, articleId);
        } catch (ArticleNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
