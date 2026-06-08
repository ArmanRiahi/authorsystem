package ir.chevil.controller;

import ir.chevil.exception.ArticleNotFoundException;
import ir.chevil.exception.DuplicatedArticleException;
import ir.chevil.exception.InvalidUsernameException;
import ir.chevil.exception.WeakPasswordException;
import ir.chevil.model.Article;
import ir.chevil.enums.ArticleStatus;
import ir.chevil.model.user.Author;
import ir.chevil.model.Category;
import ir.chevil.service.AuthorService;

import java.util.Scanner;

public class FirstController {

    static void main() {
        AuthorService service = new AuthorService();

        Category art = new Category(1, "art");
        Category tec = new Category(2, "tec");
        Category other = new Category(3, "other");

        IO.println("\n========== Author System Menu ==========");
        IO.println("Enter 'h' to help");
        try (Scanner in = new Scanner(System.in)){
            boolean running = true;
            while (running) {
                IO.print("> ");

                String choice = in.nextLine();
                IO.println();
                switch (choice.trim()) {
                    case "1" -> RegisterAuthor.run(service, in);
                    case "2" -> RegisterArticle.run(service, in, art, tec, other);
                    case "3" -> ChangeArticleStatus.run(service, in);
                    case "4" -> service.showPublishedArticle();
                    case "5" -> ArticleSearch.run(service, in);
                    case "6" -> RemoveArticle.run(service, in);
                    case "7" -> {
                        IO.print("Author ID: ");
                        int authorId = in.nextInt();
                        in.nextLine();
                        service.showArticlesByAuthorId(authorId);
                    }
                    case "q" -> {
                        IO.println("Good Luck!...");
                        running = false;
                    }
                    case "h" -> {
                        IO.println("1- Register Author");
                        IO.println("2- Add Article");
                        IO.println("3- Publish Article (Change Status)");
                        IO.println("4- Show Published Articles");
                        IO.println("5- Search Article");
                        IO.println("6- Remove Article");
                        IO.println("7- Show All Articles of an Author");
                        IO.println("q- Exit");
                        IO.println("h- Help");
                        IO.println();
                    }
                }
                IO.println();
            }
        }
    }
}
