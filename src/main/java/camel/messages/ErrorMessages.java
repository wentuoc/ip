package camel.messages;

public class ErrorMessages {
    public static final String TODO_FORMAT = "I don't understand :( Please ensure the format is as follows: " +
            "\"todo <name>\"";

    public static final String DEADLINE_FORMAT = "I don't understand :( Please ensure the format is as " +
            "follows: \"deadline <name> /by <time>\"";

    public static final String EVENT_FORMAT = "I don't understand :( Please ensure the format is as follows: " +
            "\"event <name> /from <time> /to <time>\"";

    public static final String MARK_FORMAT = "I don't understand :( Please ensure the format is as follows: \"mark " +
            "<task index>\"";

    public static final String UNMARK_FORMAT = "I don't understand :( Please ensure the format is as follows: " +
            "\"unmark <task index>\"";

    public static final String DELETE_FORMAT = "I don't understand :( Please ensure the format is as follows: " +
            "\"delete <task index>\"";

    public static final String FIND_FORMAT = "I don't understand :( Please ensure the format is as follows: " +
            "\"find <search term>\"";

    public static final String SEARCH_TERM_NOT_FOUND = "I could not find any task matching that term :(";

    public static String taskIndexOutOfRange(int numberOfTasks) {
        return String.format("Task index is out of range :( Please select within 1 and %d only", numberOfTasks);
    }

    public static final String EMPTY_TASK_LIST = "Task list is currently empty. Add a new task to start " +
            "slaying your goals :)";

    public static final String TASK_ALREADY_COMPLETED = "You have already completed this task. Yay! :)";

    public static final String TASK_NOT_COMPLETED = "You have already not completed this task :(";

    public static final String UNKNOWN_COMMAND = "I don't understand your command :( Please choose from this list " +
            "only: {todo, deadline, event, list, mark, unmark, bye, help}";

    public static String fileCorrupted(String line) {
        return "\tThe file is corrupted: " + line;
    }

    public static String createFileFailed(String line) {
        return "\tRead file failed: " + line;
    }

    public static String saveFileFailed(String line) {
        return "\tSave file failed: " + line;
    }
}
