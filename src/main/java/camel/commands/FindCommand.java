package camel.commands;

import camel.exception.CamelException;
import camel.file.Storage;
import camel.messages.ErrorMessages;
import camel.task.TaskList;
import camel.ui.Ui;

public class FindCommand extends AbstractCommand {

    String searchTerm;

    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public void execute(TaskList tasks, Ui userInterface, Storage storage) throws CamelException {
        TaskList matchingTasks = tasks.findTaskByDescription(searchTerm);
        if (matchingTasks.isEmpty()) {
            throw new CamelException(ErrorMessages.SEARCH_TERM_NOT_FOUND);
        }
        userInterface.printTaskList(matchingTasks);
    }
}
