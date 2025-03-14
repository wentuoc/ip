package camel.commands;

import camel.exception.CamelException;
import camel.file.Storage;
import camel.task.Task;
import camel.task.TaskList;
import camel.ui.Ui;

public class UnmarkCommand extends ModifyCommand {

    public UnmarkCommand(int index) {
        super(index);
    }

    /**
     * Marks a task of a specified index from TaskList {@code tasks} as not done.
     * @param tasks The TaskList to unmark the task from.
     * @param userInterface The UI object that prints the acknowledgement message.
     * @param storage Not used
     * @throws CamelException If an exception occurred when unmarking the object.
     */
    @Override
    public void execute(TaskList tasks, Ui userInterface, Storage storage) throws CamelException {
        Task currentTask = tasks.unmarkTask(index);
        userInterface.printMarkedAsNotDone(currentTask);
    }
}
