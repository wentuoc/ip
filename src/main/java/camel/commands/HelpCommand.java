package camel.commands;

import camel.exception.CamelException;
import camel.file.Storage;
import camel.task.TaskList;
import camel.ui.Ui;

public class HelpCommand extends AbstractCommand {
    @Override
    public void execute(TaskList tasks, Ui userInterface, Storage storage) throws CamelException {
        userInterface.printHelp();
    }
}
