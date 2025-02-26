package camel.commands;

import java.util.ArrayList;

import camel.exception.CamelException;
import camel.messages.*;
import camel.task.*;

public class Commands {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static String[] decodeDeadlineInput(String input) {
        int dueIndex = input.indexOf("/by");

        String description = input.substring(0, dueIndex - 1);
        String dueDate = input.substring(dueIndex + 4);
        return new String[]{description, dueDate};
    }

    public static String[] decodeEventInput(String input) {
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to", fromIndex + 1);

        String description = input.substring(0, fromIndex - 1);
        String fromDate = input.substring(fromIndex + 6, toIndex - 1);
        String toDate = input.substring(toIndex + 4);
        return new String[]{description, fromDate, toDate};
    }

    public static void addTodo(String description, boolean isDone) {
        tasks.add(new Todo(description, isDone));
    }

    public static void addDeadline(String description, boolean isDone, String doneBy) {
        tasks.add(new Deadline(description, isDone, doneBy));
    }

    public static void addEvent(String description, boolean isDone, String startTime, String endTime) {
        tasks.add(new Event(description, isDone, startTime, endTime));
    }

    public static void addTask(String taskType, String input) throws CamelException {
        switch (taskType) {
        case "todo":
            addTodo(input, false);
            break;
        case "deadline":
            try {
                String[] deadlineInputs = decodeDeadlineInput(input);
                addDeadline(deadlineInputs[0], false, deadlineInputs[1]);
                break;
            } catch (StringIndexOutOfBoundsException e) {
                throw new CamelException(ErrorMessages.DEADLINE_FORMAT);
            }
        case "event":
            try {
                String[] eventInputs = decodeEventInput(input);
                addEvent(eventInputs[0], false, eventInputs[1], eventInputs[2]);
                break;
            } catch (StringIndexOutOfBoundsException e) {
                throw new CamelException(ErrorMessages.EVENT_FORMAT);
            }
        default:
            break;
        }

        NormalMessages.printAddedTask(tasks.get(tasks.size() - 1), tasks.size());

    }

    public static void printList() throws CamelException {
        if (tasks.isEmpty()) {
            throw new CamelException(ErrorMessages.EMPTY_TASK_LIST);
        } else {
            NormalMessages.printTaskList(tasks);
        }
    }

    public static void markTask(String input) throws CamelException {
        int index = Integer.parseInt(input);
        if (tasks.isEmpty()) {
            throw new CamelException(ErrorMessages.EMPTY_TASK_LIST);
        }
        try {
            Task currentTask = tasks.get(index - 1);

            if (currentTask.isDone()) {
                throw new CamelException(ErrorMessages.TASK_ALREADY_COMPLETED);
            } else {
                currentTask.setDone();
                NormalMessages.printMarkedAsDone(currentTask);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new CamelException(ErrorMessages.taskIndexOutOfRange(tasks.size()));
        }
    }

    public static void unmarkTask(String input) throws CamelException {
        int index = Integer.parseInt(input);
        if (tasks.isEmpty()) {
            throw new CamelException(ErrorMessages.EMPTY_TASK_LIST);
        }
        try {
            Task currentTask = tasks.get(index - 1);

            if (!currentTask.isDone()) {
                throw new CamelException(ErrorMessages.TASK_NOT_COMPLETED);
            } else {
                currentTask.setNotDone();
                NormalMessages.printMarkedAsNotDone(currentTask);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new CamelException(ErrorMessages.taskIndexOutOfRange(tasks.size()));
        }
    }

    public static void deleteTask(String input) throws CamelException {
        int index = Integer.parseInt(input);
        if (tasks.isEmpty()) {
            throw new CamelException(ErrorMessages.EMPTY_TASK_LIST);
        }
        try {
            NormalMessages.printDeleteTask(tasks.get(index - 1));
            tasks.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new CamelException(ErrorMessages.taskIndexOutOfRange(tasks.size()));
        }
    }

    public static ArrayList<String> fileFormat() {
        ArrayList<String> lines = new ArrayList<>();

        for (Task task : tasks) {
            lines.add(task.toFileFormat());
        }

        return lines;
    }
}
