public class Deadline extends Task {
    private String doneBy;

    public Deadline(String description, String doneBy) {
        super(description);
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
}
