package Main.Validation;

public class ExceededMaxLanes extends Exception {
    public ExceededMaxLanes (String errorMessage) {
        super(errorMessage);
    }
}
