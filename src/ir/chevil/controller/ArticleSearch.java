package ir.chevil.controller;

import ir.chevil.exception.ArticleNotFoundException;
import ir.chevil.model.Article;
import ir.chevil.service.AuthorService;

import java.util.Scanner;

public class ArticleSearch {
    public static void run(AuthorService service, Scanner in) {

        IO.println("--- Article search ---");
        IO.print("Author ID: ");
        int authorId = in.nextInt();
        IO.print("Article ID: ");
        int articleId = in.nextInt();
        in.nextLine();
        try {
            Article found = service.searchArticle(authorId, articleId);
            IO.println("Article founded:\n" + found);
        } catch (ArticleNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
