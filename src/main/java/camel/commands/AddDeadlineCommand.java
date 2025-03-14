package camel.commands;

import camel.exception.CamelException;
import camel.file.Storage;
import camel.task.Task;
import camel.task.TaskList;
import camel.ui.Ui;

public class AddDeadlineCommand extends AddTaskCommand {

    private final String doneBy;

    public AddDeadlineCommand(String description, boolean isDone, String doneBy) {
        super(description, isDone);
        this.doneBy = doneBy;
    }

    /**
     * Adds a new Deadline object into TaskList {@code tasks}.
     * @param tasks The TaskList to add the Deadline object into.
     * @param userInterface The UI object that prints the acknowledgement message.
     * @param storage Not used
     * @throws CamelException If an exception occurred when creating a new Deadline object.
     */
    @Override
    public void execute(TaskList tasks, Ui userInterface, Storage storage) throws CamelException {
        Task newDeadline = tasks.addDeadline(description, isDone, doneBy);
        printAcknowledgement(userInterface, newDeadline, tasks);
    }
}
