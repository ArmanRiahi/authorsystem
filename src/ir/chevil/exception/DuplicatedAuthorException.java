package ir.chevil.exception;

public class DuplicatedAuthorException extends RuntimeException {
    public DuplicatedAuthorException(String message) {
        super(message);
    }
}
