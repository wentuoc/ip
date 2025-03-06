package camel.commands;

import camel.exception.CamelException;
import camel.messages.ErrorMessages;
import camel.task.TaskList;
import camel.ui.Ui;

public class Parser {

    private final Ui userInterface;
    private final Commands commands;

    public Parser(TaskList tasks) {
        userInterface = new Ui();
        commands = new Commands(tasks);
    }

    private static String[] decodeDeadlineInput(String input) {
        int dueIndex = input.indexOf("/by");

        String description = input.substring(0, dueIndex - 1);
        String dueDate = input.substring(dueIndex + 4);
        return new String[]{description, dueDate};
    }

    private static String[] decodeEventInput(String input) {
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to", fromIndex + 1);

        String description = input.substring(0, fromIndex - 1);
        String fromDate = input.substring(fromIndex + 6, toIndex - 1);
        String toDate = input.substring(toIndex + 4);
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
            if (!input.contains(" ")) {
                throw new CamelException(ErrorMessages.TODO_FORMAT);
            }
            commands.addTodo(item,false);
        case "deadline":
            params = decodeDeadlineInput(item);
            commands.addDeadline(params[0], false, params[1]);
            break;
        case "event":
            params = decodeEventInput(item);
            commands.addEvent(params[0], false, params[1], params[2]);
            break;
        case "list":
            userInterface.printTaskList(commands.print());
            break;
        case "mark":
            commands.mark(item);
            break;
        case "unmark":
            commands.unmark(item);
            break;
        case "delete":
            commands.delete(item);
            break;
        case "help":
            userInterface.printHelp();
            break;
        case "bye":
            return true;
        default:
            throw new CamelException(ErrorMessages.UNKNOWN_COMMAND);
        }
        return false;
    }
}
