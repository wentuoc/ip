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

    @Override
    public void execute(TaskList tasks, Ui userInterface, Storage storage) throws CamelException {
        Task newDeadline = tasks.addDeadline(description, isDone, doneBy);
        printAcknowledgement(userInterface, newDeadline, tasks);
    }
}
