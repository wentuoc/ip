package camel.commands;

import camel.exception.CamelException;
import camel.file.Storage;
import camel.task.Task;
import camel.task.TaskList;
import camel.ui.Ui;

public class MarkCommand extends ModifyCommand {

    public MarkCommand(int index) {
        super(index);
    }

    @Override
    public void execute(TaskList tasks, Ui userInterface, Storage storage) throws CamelException {
        Task currentTask = tasks.markTask(index);
        userInterface.printMarkedAsDone(currentTask);
    }
}
