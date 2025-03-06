package camel.commands;

import camel.exception.CamelException;
import camel.file.Storage;
import camel.messages.ErrorMessages;
import camel.task.TaskList;
import camel.ui.Ui;

public class PrintListCommand extends AbstractCommand {

    @Override
    public void execute(TaskList tasks, Ui userInterface, Storage storage) throws CamelException {
        if (tasks.getSize() == 0) {
            throw new CamelException(ErrorMessages.EMPTY_TASK_LIST);
        }
        userInterface.printTaskList(tasks);
    }
}
