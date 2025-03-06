package camel.ui;

import camel.commands.Commands;
import camel.exception.CamelException;
import camel.messages.ErrorMessages;
import camel.task.Task;
import camel.task.TaskList;

public class Parser {

    private final Commands commands;
    private final Ui userInterface;
    private final TaskList tasks;
    private final int LENGTH_OF_BY = 4;
    private final int LENGTH_OF_TO = 4;
    private final int LENGTH_OF_FROM = 6;

    public Parser(TaskList tasks) {
        commands = new Commands(tasks);
        this.tasks = tasks;
        userInterface = new Ui();
    }

    private String[] decodeDeadlineInput(String input) {
        int dueIndex = input.indexOf("/by");

        String description = input.substring(0, dueIndex - 1);
        String dueDate = input.substring(dueIndex + LENGTH_OF_BY);
        return new String[]{description, dueDate};
    }

    private String[] decodeEventInput(String input) {
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to", fromIndex + 1);

        String description = input.substring(0, fromIndex - 1);
        String fromDate = input.substring(fromIndex + LENGTH_OF_FROM, toIndex - 1);
        String toDate = input.substring(toIndex + LENGTH_OF_TO);
        return new String[]{description, fromDate, toDate};
    }

    public boolean parseInput(String input) throws CamelException {
        String cmd = input;
        String item = "";
        String[] params;

        int firstSpaceIndex = input.indexOf(' ');
        if (firstSpaceIndex != -1) {
            cmd = input.substring(0, firstSpaceIndex);
            item = input.substring(firstSpaceIndex + 1);
        }

        switch (cmd) {
        case "todo":
            if (item.isEmpty()) {
                throw new CamelException(ErrorMessages.TODO_FORMAT);
            }
            Task addedTask = commands.addTodo(item,false);
            userInterface.printAddedTask(addedTask, tasks.getSize());
            break;
        case "deadline":
            try {
                params = decodeDeadlineInput(item);
                Task addedDeadline = commands.addDeadline(params[0], false, params[1]);
                userInterface.printAddedTask(addedDeadline, tasks.getSize());
                break;
            } catch (IndexOutOfBoundsException e) {
                throw new CamelException(ErrorMessages.DEADLINE_FORMAT);
            }
        case "event":
            try {
                params = decodeEventInput(item);
                Task addedEvent = commands.addEvent(params[0], false, params[1], params[2]);
                userInterface.printAddedTask(addedEvent, tasks.getSize());
                break;
            } catch (IndexOutOfBoundsException e) {
                throw new CamelException(ErrorMessages.EVENT_FORMAT);
            }
        case "list":
            commands.printList();
            break;
        case "mark":
            try {
                int index = Integer.parseInt(item);
                Task markedTask = commands.mark(index - 1);
                userInterface.printMarkedAsDone(markedTask);
                break;
            } catch (NumberFormatException e) {
                throw new CamelException(ErrorMessages.MARK_FORMAT);
            }
        case "unmark":
            try {
                int index = Integer.parseInt(item);
                Task unmarkedTask = commands.unmark(index - 1);
                userInterface.printMarkedAsNotDone(unmarkedTask);
                break;
            } catch (NumberFormatException e) {
                throw new CamelException(ErrorMessages.UNMARK_FORMAT);
            }
        case "delete":
            try {
                int index = Integer.parseInt(item);
                Task deletedTask = commands.delete(index - 1);
                userInterface.printDeletedTask(deletedTask);
                break;
            } catch (NumberFormatException e) {
                throw new CamelException(ErrorMessages.DELETE_FORMAT);
            }
        case "help":
            commands.printHelp();
            break;
        case "bye":
            return true;
        default:
            throw new CamelException(ErrorMessages.UNKNOWN_COMMAND);
        }
        return false;
    }
}
