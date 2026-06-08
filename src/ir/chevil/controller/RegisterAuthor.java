package ir.chevil.controller;

import ir.chevil.exception.InvalidUsernameException;
import ir.chevil.exception.WeakPasswordException;
import ir.chevil.model.user.Author;
import ir.chevil.service.AuthorService;

import java.util.Scanner;

public class RegisterAuthor {
    public static void run(AuthorService service, Scanner in) {
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
        }    }
}
