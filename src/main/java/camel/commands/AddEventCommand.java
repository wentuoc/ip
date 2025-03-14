package camel.commands;

import camel.exception.CamelException;
import camel.file.Storage;
import camel.task.Task;
import camel.task.TaskList;
import camel.ui.Ui;

public class AddEventCommand extends AddTaskCommand {

    private final String startTime;
    private final String endTime;

    public AddEventCommand(String description, boolean isDone, String startTime, String endTime) {
        super(description, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Adds a new Event object into TaskList {@code tasks}.
     * @param tasks The TaskList to add the Event object into.
     * @param userInterface The UI object that prints the acknowledgement message.
     * @param storage Not used
     * @throws CamelException If an exception occurred when creating a new Event object.
     */
    @Override
    public void execute(TaskList tasks, Ui userInterface, Storage storage) throws CamelException {
        Task newEvent = tasks.addEvent(description, isDone, startTime, endTime);
        printAcknowledgement(userInterface, newEvent, tasks);
    }
}
