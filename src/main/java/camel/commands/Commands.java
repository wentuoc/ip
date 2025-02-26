package camel.commands;

import camel.exception.CamelException;
import camel.messages.*;
import camel.task.*;

public class Commands {
    private static final int TASK_SIZE = 100;

    private static int numberOfTasks = 0;
    private static Task[] tasks = new Task[TASK_SIZE];

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

    public static void addTask(String taskType, String input) throws CamelException {
        Task newTask;

        switch (taskType) {
        case "todo":
            newTask = new Todo(input);
            tasks[numberOfTasks] = newTask;
            break;
        case "deadline":
            try {
                String[] deadlineInputs = decodeDeadlineInput(input);
                newTask = new Deadline(deadlineInputs[0], deadlineInputs[1]);
                tasks[numberOfTasks] = newTask;
                break;
            } catch (StringIndexOutOfBoundsException e) {
                throw new CamelException(ErrorMessages.DEADLINE_FORMAT);
            }
        case "event":
            try {
                String[] eventInputs = decodeEventInput(input);
                newTask = new Event(eventInputs[0], eventInputs[1], eventInputs[2]);
                tasks[numberOfTasks] = newTask;
                break;
            } catch (StringIndexOutOfBoundsException e) {
                throw new CamelException(ErrorMessages.EVENT_FORMAT);
            }
        default:
            break;
        }

        NormalMessages.printAddedTask(tasks[numberOfTasks], numberOfTasks + 1);
        numberOfTasks++;
    }

    public static void printList() throws CamelException {
        if (numberOfTasks == 0) {
            throw new CamelException(ErrorMessages.EMPTY_TASK_LIST);
        } else {
            NormalMessages.printTaskList(tasks, numberOfTasks);
        }
    }

    public static void markTask(String input) throws CamelException {
        int index = Integer.parseInt(input);
        if (index >= 1 && index <= numberOfTasks) {
            Task currentTask = tasks[index - 1];

            if (currentTask.isDone()) {
                throw new CamelException(ErrorMessages.TASK_ALREADY_COMPLETED);
            } else {
                currentTask.setDone();
                NormalMessages.printMarkedAsDone(currentTask);
            }
        }
        else if (numberOfTasks == 0) {
            throw new CamelException(ErrorMessages.EMPTY_TASK_LIST);
        }
        else {
            throw new CamelException(ErrorMessages.taskIndexOutOfRange(numberOfTasks));
        }
    }

    public static void unmarkTask(String input) throws CamelException {
        int index = Integer.parseInt(input);
        if (index >= 1 && index <= numberOfTasks) {
            Task currentTask = tasks[index - 1];

            if (!currentTask.isDone()) {
                throw new CamelException(ErrorMessages.TASK_NOT_COMPLETED);
            } else {
                currentTask.setNotDone();
                NormalMessages.printMarkedAsNotDone(currentTask);
            }
        }
        else if (numberOfTasks == 0) {
            throw new CamelException(ErrorMessages.EMPTY_TASK_LIST);
        }
        else {
            throw new CamelException(ErrorMessages.taskIndexOutOfRange(numberOfTasks));
        }
    }
}
