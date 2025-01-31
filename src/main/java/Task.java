public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
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

    public void printTask() {
        if (isDone()) {
            System.out.println("[X] " + description);
        } else {
            System.out.println("[ ] " + description);
        }
    }
}
