package camel.commands;

import camel.task.Task;
import camel.task.TaskList;
import camel.ui.Ui;

abstract class AddTaskCommand extends AbstractCommand {

    protected String description;
    protected boolean isDone;

    public AddTaskCommand(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    protected void printAcknowledgement(Ui userInterface, Task task, TaskList tasks) {
        userInterface.printAddedTask(task, tasks.getSize());
    }
}
