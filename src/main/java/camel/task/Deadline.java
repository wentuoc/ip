package camel.task;

public class Deadline extends Task {
    private String doneBy;

    public Deadline(String description, boolean isDone, String doneBy) {
        super(description, isDone);
        this.doneBy = doneBy;
    }

    public String getDoneBy() {
        return doneBy;
    }

    public void setDoneBy(String doneBy) {
        this.doneBy = doneBy;
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
