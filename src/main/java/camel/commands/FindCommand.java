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

    /**
     * Searches a TaskList for tasks that contain the specified search term.
     * @param tasks The TaskList to search in.
     * @param userInterface The UI object that prints the TaskList.
     * @param storage Not used
     * @throws CamelException If an exception occurred when searching for the object.
     */
    @Override
    public void execute(TaskList tasks, Ui userInterface, Storage storage) throws CamelException {
        TaskList matchingTasks = tasks.findTaskByDescription(searchTerm);
        if (matchingTasks.isEmpty()) {
            throw new CamelException(ErrorMessages.SEARCH_TERM_NOT_FOUND);
        }
        userInterface.printTaskList(matchingTasks);
    }
}
