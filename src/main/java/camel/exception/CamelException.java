package camel.exception;

public class CamelException extends Exception {
    private static final String LINE_BREAK = "    ____________________________________________________________";

    public CamelException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return LINE_BREAK + "\n\t" + super.getMessage() + "\n" + LINE_BREAK;
    }
}
