package ir.chevil.controller.login;

import ir.chevil.exception.*;
import ir.chevil.model.user.Author;
import ir.chevil.service.AuthorService;

import java.util.Scanner;

public class RegisterAuthor {
    public static void run(AuthorService service, Scanner in) {
        IO.println("---Register new author ---");
        IO.print("First name: ");
        String firstName = in.nextLine();
        IO.print("Last name: ");
        String lastName = in.nextLine();
        IO.print("Username: ");
        String userName = in.nextLine();
        IO.print("Password: ");
        String password = in.nextLine();
        IO.print("Enter your password again: ");
        String passwordAgain = in.nextLine();

        Author author = new Author(firstName, lastName, userName, password);
        try {
            checkPassword(password, passwordAgain);
            service.registerAuthor(author);
        } catch (InvalidUsernameException | WeakPasswordException | DuplicatedAuthorException | PasswordConfirmationException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void checkPassword(String password, String passwordAgain) throws PasswordConfirmationException {
        if (!password.equals(passwordAgain)) {
            throw new PasswordConfirmationException("Passwords do not match!!");
        }
    }
}
