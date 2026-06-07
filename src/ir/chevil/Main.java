package ir.chevil;

import ir.chevil.exception.ArticleNotFoundException;
import ir.chevil.exception.InvalidUsernameException;
import ir.chevil.exception.WeakPasswordException;
import ir.chevil.model.Article;
import ir.chevil.model.ArticleStatus;
import ir.chevil.model.Author;
import ir.chevil.model.Category;
import ir.chevil.service.AuthorService;

import java.util.Scanner;

public class Main {

    static void main() {
        AuthorService service = new AuthorService();

        Category art = new Category(1, "art");
        Category tec = new Category(2, "tec");
        Category other = new Category(3, "other");

        IO.println("\n========== Author System Menu ==========");
        IO.println("Enter 'h' to show menu.");
        try (Scanner in = new Scanner(System.in)){
            boolean running = true;
            while (running) {
                IO.print("> ");

                String choice = in.nextLine();
                IO.println();
                switch (choice.trim()) {
                    case "1" -> {
                        IO.println("---Register new author ---");
                        IO.print("Author ID: ");
                        int id = in.nextInt();
                        in.nextLine();
                        IO.print("First name: ");
                        String firstName = in.nextLine();
                        IO.print("Last name: ");
                        String lastName = in.nextLine();
                        IO.print("Username: ");
                        String userName = in.nextLine();
                        IO.print("Password: ");
                        String password = in.nextLine();

                        Author author = new Author(id, firstName, lastName, userName, password);
                        try {
                            service.registerAuthor(author);
                        } catch (InvalidUsernameException | WeakPasswordException e) {
                            System.err.println(e.getMessage());
                        }
                    }

                    case "2" -> {
                        IO.println("--- Register new article ---");
                        IO.print("Author ID: ");
                        int authorId = in.nextInt();
                        in.nextLine();
                        if (service.findAuthorById(authorId) == null) {
                            System.err.println("Error: author not founded.");
                            break;
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

                        Article article = new Article(articleId, title, content, seletcedCategory);
                        service.registerArticleForAuthor(authorId, article);
                    }

                    case "3" -> {
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

                    case "4" -> service.showPublishedArticle();

                    case "5" -> {
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

                    case "6" -> {
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
