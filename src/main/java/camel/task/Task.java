package camel.task;

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

    @Override
    public String toString() {
        if (isDone()) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }

    public String toFileFormat() {
        return Boolean.toString(isDone) + "," + description;
    }
}
