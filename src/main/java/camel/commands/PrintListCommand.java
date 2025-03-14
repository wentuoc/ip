package camel.commands;

import camel.exception.CamelException;
import camel.file.Storage;
import camel.messages.ErrorMessages;
import camel.task.TaskList;
import camel.ui.Ui;

public class PrintListCommand extends AbstractCommand {

    /**
     * Prints a list of all tasks in from TaskList {@code tasks}.
     * @param tasks The TaskList to print the tasks from.
     * @param userInterface The UI object that prints the messages.
     * @param storage Not used
     * @throws CamelException If an exception occurred when printing the object.
     */
    @Override
    public void execute(TaskList tasks, Ui userInterface, Storage storage) throws CamelException {
        if (tasks.getSize() == 0) {
            throw new CamelException(ErrorMessages.EMPTY_TASK_LIST);
        }
        userInterface.printTaskList(tasks);
    }
}
