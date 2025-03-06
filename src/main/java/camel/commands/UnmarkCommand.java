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

    @Override
    public void execute(TaskList tasks, Ui userInterface, Storage storage) throws CamelException {
        Task currentTask = tasks.unmarkTask(index);
        userInterface.printMarkedAsNotDone(currentTask);
    }
}
