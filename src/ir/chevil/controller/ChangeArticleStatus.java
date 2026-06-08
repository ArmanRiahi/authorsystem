package ir.chevil.controller;

import ir.chevil.enums.ArticleStatus;
import ir.chevil.exception.ArticleNotFoundException;
import ir.chevil.service.AuthorService;

import java.util.Scanner;

public class ChangeArticleStatus {
    public static void run(AuthorService service, Scanner in) {

        IO.println("--- Change article status ---");
        IO.print("Author ID: ");
        int authorId = in.nextInt();
        IO.print("Article ID: ");
        int articleId = in.nextInt();
        in.nextLine();
        IO.println("1) DRAFT  2) REVIEWING  3) PUBLISHED  4) REJECTED");
        IO.print("Chose new status: ");
        int statusChoice = in.nextInt();
        in.nextLine();

        ArticleStatus status = ArticleStatus.DRAFT;
        if (statusChoice == 2) status = ArticleStatus.REVIEWING;
        else if (statusChoice == 3) status = ArticleStatus.PUBLISHED;
        else if (statusChoice == 4) status = ArticleStatus.REJECTED;

        try {
            service.updateArticleStatus(authorId, articleId, status);
        } catch (ArticleNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
