package camel.commands;

import camel.file.Storage;
import camel.task.Task;
import camel.task.TaskList;
import camel.ui.Ui;

public class AddTodoCommand extends AddTaskCommand {

    public AddTodoCommand(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public void execute(TaskList tasks, Ui userInterface, Storage storage) {
        Task newTodo = tasks.addTodo(description, isDone);
        printAcknowledgement(userInterface, newTodo, tasks);
    }
}
