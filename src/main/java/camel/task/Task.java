package camel.task;

import camel.exception.CamelException;
import camel.messages.ErrorMessages;

import java.time.DateTimeException;
import java.time.LocalDateTime;

public class Task {
    private final String description;
    private boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone() {
        isDone = true;
    }

    public void setNotDone() {
        isDone = false;
    }

    /**
     * Converts a date time input represented as a String in {@code yyyy-mm-dd hh:mm} format into a LocalDateTime
     * object. If the input provided is of a non-standard format, this function attempts to parse it and convert it
     * into the standard date time format.
     *
     * @param input The date time input as a String.
     * @return The date time as a LocalDateTime object
     * @throws CamelException If an error was encountered when parsing the input, such as if it was of a wrong format.
     */
    protected LocalDateTime convertDateTime(String input) throws CamelException {
        try {
            String formattedDateTime = formatDateTime(input);
            return LocalDateTime.parse(formattedDateTime);
        } catch (DateTimeException e) {
            throw new CamelException(ErrorMessages.INCORRECT_DATE_TIME_INPUT);
        }
    }

    private String formatDateTime(String input) throws DateTimeException {
        String[] splitInputs = input.split(" ");
        if (splitInputs.length != 2) {
            throw new DateTimeException(ErrorMessages.INCORRECT_DATE_TIME_INPUT);
        }
        String date;
        String time;

        if (splitInputs[0].length() > splitInputs[1].length()) { //date-time format
            date = formatDate(splitInputs[0]);
            time = formatTime(splitInputs[1]);
        } else { //time-date format
            date = formatDate(splitInputs[1]);
            time = formatTime(splitInputs[0]);
        }
        return date + "T" + time;
    }

    private String formatTime(String input) {
        if (input.contains(".")) {
            input = input.replace(".", ":");
        }
        return input;
    }

    private String formatDate(String input) throws DateTimeException {
        if (input.contains("/")) {
            input = input.replace('/','-');
        }
        String[] splitInputs = input.split("-");
        if (splitInputs.length != 3) {
            throw new DateTimeException(ErrorMessages.INCORRECT_DATE_TIME_INPUT);
        }
        if (splitInputs[0].length() == 4) { //yyyy-mm-dd format
            return input;
        } else if (splitInputs[0].length() == 2 && splitInputs[2].length() == 4) { //dd-mm-yyyy format
            return splitInputs[2] + "-" + splitInputs[1] + "-" + splitInputs[0];
        } else if (splitInputs[0].length() == 1) { //d-m-yyyy format
            splitInputs[0] = "0" + splitInputs[0];
            if (splitInputs[1].length() == 1) {
                splitInputs[1] = "0" + splitInputs[1];
            }
            if (splitInputs[2].length() == 2) {
                splitInputs[2] = "20" + splitInputs[2];
            }
            return splitInputs[2] + "-" + splitInputs[1] + "-" + splitInputs[0];
        } else {
            throw new DateTimeException(ErrorMessages.INCORRECT_DATE_TIME_INPUT);
        }
    }

    protected String printDateTime(LocalDateTime dateTime) {
        String dateTimeInString = dateTime.toString();
        return dateTimeInString.replace("T"," ");
    }

    @Override
    public String toString() {
        if (isDone()) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }

    public String toFileFormat() {
        return isDone + "," + description;
    }
}
