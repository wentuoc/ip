package camel.commands;

import camel.exception.CamelException;
import camel.file.Storage;
import camel.task.TaskList;
import camel.ui.Ui;

public class ByeCommand extends AbstractCommand {

    public ByeCommand() {
        hasEnded = true;
    }

    @Override
    public void execute(TaskList tasks, Ui userInterface, Storage storage) throws CamelException {
        assert true; //do nothing
    }
}
