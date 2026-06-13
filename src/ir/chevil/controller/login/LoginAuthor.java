package ir.chevil.controller.login;

import ir.chevil.exception.UsernameNotFoundException;
import ir.chevil.model.user.Author;
import ir.chevil.service.AuthorService;

import java.util.Scanner;

public class LoginAuthor {
    public static Author run(AuthorService service, Scanner in) {
        IO.println("--- Login ---");
        IO.print("Username: ");
        String username = in.nextLine().trim();
        IO.print("Password: ");
        String password = in.nextLine().trim();

        try {
            Author author = service.findAuthorByUsername(username);
            if (service.checkUsernameAndPassword(author, username, password)) {
                IO.println("Login successful.");
                return author;
            }
            System.err.println("Error: Wrong password.");
            return null;
        } catch (UsernameNotFoundException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}

