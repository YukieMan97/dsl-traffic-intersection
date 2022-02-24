package Main.Validation;

public class InvalidStateConfigException extends Exception {
    public InvalidStateConfigException (String errorMessage) {
        super(errorMessage);
    }
}
