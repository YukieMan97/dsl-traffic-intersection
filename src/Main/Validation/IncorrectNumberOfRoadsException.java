package Main.Validation;

public class IncorrectNumberOfRoadsException extends Exception {
    public IncorrectNumberOfRoadsException (String errorMessage) {
        super(errorMessage);
    }
}
