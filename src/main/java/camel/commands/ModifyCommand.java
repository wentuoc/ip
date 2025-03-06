package camel.commands;

import camel.exception.CamelException;
import camel.file.Storage;
import camel.task.Task;
import camel.task.TaskList;
import camel.ui.Ui;

abstract class ModifyCommand extends AbstractCommand {

    protected final int index;

    public ModifyCommand(int index) {
        this.index = index;
    }
}
