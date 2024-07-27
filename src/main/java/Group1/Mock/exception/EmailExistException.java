package Group1.Mock.exception;

public class EmailExistException extends RuntimeException{
    public EmailExistException (String message) {
        super(message);
    }
}
