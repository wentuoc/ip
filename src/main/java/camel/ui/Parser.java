package camel.ui;

import camel.commands.*;
import camel.exception.CamelException;
import camel.messages.ErrorMessages;

/**
 * Parses the user's inputs and fetches the corresponding Command class
 */
public class Parser {

    private final int LENGTH_OF_BY = 4;
    private final int LENGTH_OF_TO = 4;
    private final int LENGTH_OF_FROM = 6;

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

    /**
     * Parses the input string and splits it into the command and arguments.
     * Returns the corresponding Command object.
     *
     * @param input Input from user.
     * @return Corresponding Command object.
     * @throws CamelException If an unknown command or an incorrect number of arguments is supplied.
     */
    public AbstractCommand parseInput(String input) throws CamelException {
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
            return new AddTodoCommand(item, false);
        case "deadline":
            try {
                params = decodeDeadlineInput(item);
                return new AddDeadlineCommand(params[0], false, params[1]);
            } catch (IndexOutOfBoundsException e) {
                throw new CamelException(ErrorMessages.DEADLINE_FORMAT);
            }
        case "event":
            try {
                params = decodeEventInput(item);
                return new AddEventCommand(params[0], false, params[1], params[2]);
            } catch (IndexOutOfBoundsException e) {
                throw new CamelException(ErrorMessages.EVENT_FORMAT);
            }
        case "list":
            return new PrintListCommand();
        case "mark":
            try {
                int index = Integer.parseInt(item);
                return new MarkCommand(index - 1);
            } catch (NumberFormatException e) {
                throw new CamelException(ErrorMessages.MARK_FORMAT);
            }
        case "unmark":
            try {
                int index = Integer.parseInt(item);
                return new UnmarkCommand(index - 1);
            } catch (NumberFormatException e) {
                throw new CamelException(ErrorMessages.UNMARK_FORMAT);
            }
        case "delete":
            try {
                int index = Integer.parseInt(item);
                return new DeleteCommand(index - 1);
            } catch (NumberFormatException e) {
                throw new CamelException(ErrorMessages.DELETE_FORMAT);
            }
        case "find":
            if (item.isEmpty()) {
                throw new CamelException(ErrorMessages.FIND_FORMAT);
            }
            return new FindCommand(item);
        case "help":
            return new HelpCommand();
        case "bye":
            return new ByeCommand();
        default:
            throw new CamelException(ErrorMessages.UNKNOWN_COMMAND);
        }
    }
}
