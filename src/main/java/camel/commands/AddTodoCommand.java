package camel.commands;

import camel.exception.CamelException;
import camel.file.Storage;
import camel.task.Task;
import camel.task.TaskList;
import camel.ui.Ui;

public class AddTodoCommand extends AddTaskCommand {

    public AddTodoCommand(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Adds a new Todo object into TaskList {@code tasks}.
     * @param tasks The TaskList to add the Todo object into.
     * @param userInterface The UI object that prints the acknowledgement message.
     * @param storage Not used
     */
    @Override
    public void execute(TaskList tasks, Ui userInterface, Storage storage) {
        Task newTodo = tasks.addTodo(description, isDone);
        printAcknowledgement(userInterface, newTodo, tasks);
    }
}
