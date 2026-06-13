package ir.chevil.controller;

import ir.chevil.controller.authorpage.ArticleSearch;
import ir.chevil.controller.authorpage.ChangeArticleStatus;
import ir.chevil.controller.authorpage.RegisterArticle;
import ir.chevil.controller.authorpage.RemoveArticle;
import ir.chevil.controller.login.LoginAuthor;
import ir.chevil.controller.login.RegisterAuthor;
import ir.chevil.model.Category;
import ir.chevil.model.user.Author;
import ir.chevil.service.ArticleService;
import ir.chevil.service.AuthorService;
import ir.chevil.util.ApplicationContext;

import java.util.Scanner;

public class MainController {

    static void main() {
        AuthorService authorService = new AuthorService(
                ApplicationContext.getAuthorRepo(), ApplicationContext.getAuthors());
        ArticleService articleService = new ArticleService(
                ApplicationContext.getArticleRepo(), ApplicationContext.getArticles());

        Category art = new Category(1, "art");
        Category tec = new Category(2, "tec");
        Category other = new Category(3, "other");
        ApplicationContext.getCategories().add(art);
        ApplicationContext.getCategories().add(tec);
        ApplicationContext.getCategories().add(other);

        try (Scanner in = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                Author current = guestPage(authorService, in);
                if (current == null) {
                    running = false;
                } else {
                    ApplicationContext.setAuthor(current);
                    authorPage(articleService, in, current, art, tec, other);
                    ApplicationContext.setAuthor(null);
                }
            }
            IO.println("Good Luck!...");
        }
    }

    private static Author guestPage(AuthorService service, Scanner in) {
        IO.println("\n===== Welcome to authors system =====");
        IO.println("Enter 'h' for help.");

        while (true) {
            IO.print("> ");
            switch (in.nextLine().trim()) {
                case "1" -> RegisterAuthor.run(service, in);
                case "2" -> {
                    Author author = LoginAuthor.run(service, in);
                    if (author != null) return author;
                }
                case "q" -> { return null; }
                case "h" -> {
                    IO.println("1- Register");
                    IO.println("2- Login");
                    IO.println("q- Exit");
                    IO.println("h- Help");
                }
                default -> {
                    IO.println("Invalid input. Try again.");
                }
            }
            IO.println();
        }
    }

    private static void authorPage(ArticleService service, Scanner in, Author author,
                                   Category art, Category tec, Category other) {
        IO.println("\n===== Author Menu (" + author.getUsername() + ") =====");
        IO.println("Enter 'h' for help.");

        boolean loggedIn = true;
        while (loggedIn) {
            IO.print("> ");
            switch (in.nextLine().trim()) {
                case "1" -> RegisterArticle.run(service, in, author, art, tec, other);
                case "2" -> ChangeArticleStatus.run(service, in);
                case "3" -> service.findPublishedArticle().forEach(IO::println);
                case "4" -> ArticleSearch.run(service, in);
                case "5" -> RemoveArticle.run(service, in);
                case "6" -> {
                    if (author.getArticles().isEmpty()) IO.println("No articles.");
                    else author.getArticles().forEach(IO::println);
                }
                case "q" -> loggedIn = false;
                case "h" -> {
                    IO.println("1- Add Article");
                    IO.println("2- Publish Article (Change Status)");
                    IO.println("3- Show Published Articles");
                    IO.println("4- Search Article");
                    IO.println("5- Remove Article");
                    IO.println("6- Show My Articles");
                    IO.println("q- Logout");
                    IO.println("h- Help");
                }
            }
            IO.println();
        }
    }
}
