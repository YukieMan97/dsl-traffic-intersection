package Main.Validation;

public class InvalidLaneException extends Exception {
    public InvalidLaneException (String errorMessage) {
        super(errorMessage);
    }
}
