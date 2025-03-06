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

    @Override
    public void execute(TaskList tasks, Ui userInterface, Storage storage) throws CamelException {
        Task currentTask = tasks.deleteTask(index);
        userInterface.printDeletedTask(currentTask);
    }
}
