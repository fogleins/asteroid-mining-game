package Exceptions;

public class ActionFailedException extends Exception {
    public ActionFailedException(String errorMessage) {
        super(errorMessage);
    }
}
