package ir.chevil.controller;

import ir.chevil.exception.DuplicatedArticleException;
import ir.chevil.model.Article;
import ir.chevil.model.Category;
import ir.chevil.service.AuthorService;

import java.util.Scanner;

public class RegisterArticle {
    public static void run(AuthorService service, Scanner in, Category art, Category tec, Category other) {
        IO.println("--- Register new article ---");
        IO.print("Author ID: ");
        int authorId = in.nextInt();
        in.nextLine();
        if (service.findAuthorById(authorId) == null) {
            System.err.println("Error: author not founded.");
            return;
        }
        IO.print("Article ID: ");
        int articleId = in.nextInt();
        in.nextLine();
        IO.print("Article title: ");
        String title = in.nextLine();
        IO.print("Article content: ");
        String content = in.nextLine();
        IO.println("Chose a category: 1) art 2) tec 3) other 4) non-category");
        IO.print("> ");
        int categoryChoice = in.nextInt();
        in.nextLine();
        Category seletcedCategory = null;
        if (categoryChoice == 1) seletcedCategory = art;
        else if (categoryChoice == 2) seletcedCategory = tec;
        else if (categoryChoice == 3) seletcedCategory = other;

        try {
            Article article = new Article(articleId, title, content, seletcedCategory);
            service.registerArticleForAuthor(authorId, article);
        } catch (DuplicatedArticleException e) {
            System.err.println(e.getMessage());
        }
    }
}
