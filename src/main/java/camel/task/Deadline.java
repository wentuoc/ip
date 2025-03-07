package camel.task;

import camel.exception.CamelException;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime doneBy;

    public Deadline(String description, boolean isDone, String doneBy) throws CamelException {
        super(description, isDone);
        this.doneBy = convertDateTime(doneBy);
    }

    public String getDoneBy() {
        return doneBy.toString();
    }

    public void setDoneBy(String doneBy) throws CamelException {
        this.doneBy = convertDateTime(doneBy);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + doneBy + ")";
    }

    @Override
    public String toFileFormat() {
        return "D," + super.toFileFormat() + "," + doneBy;
    }
}
