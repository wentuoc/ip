package camel.commands;

import camel.exception.CamelException;
import camel.file.Storage;
import camel.task.Task;
import camel.task.TaskList;
import camel.ui.Ui;

public class DeleteCommand extends ModifyCommand {

    public DeleteCommand(int index) {
        super(index);
    }

    /**
     * Deletes a task from TaskList {@code tasks}.
     * @param tasks The TaskList to delete the task from.
     * @param userInterface The UI object that prints the acknowledgement message.
     * @param storage Not used
     * @throws CamelException If an exception occurred when deleting the object.
     */
    @Override
    public void execute(TaskList tasks, Ui userInterface, Storage storage) throws CamelException {
        Task currentTask = tasks.deleteTask(index);
        userInterface.printDeletedTask(currentTask);
    }
}
