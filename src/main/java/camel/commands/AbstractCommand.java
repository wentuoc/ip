package camel.commands;

import camel.exception.CamelException;
import camel.file.Storage;
import camel.task.TaskList;
import camel.ui.Ui;


public abstract class AbstractCommand {

    protected boolean hasEnded = false;

    public abstract void execute(TaskList tasks, Ui userInterface, Storage storage) throws CamelException;

    public boolean hasEnded() {
        return hasEnded;
    }
}
