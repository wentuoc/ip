package camel.ui;

import java.util.Scanner;
import camel.task.*;
import camel.exception.*;
import camel.messages.*;

public class Camel {
    private static final int TASK_SIZE = 100;

    private static int numberOfTasks = 0;
    private static Task[] tasks = new Task[TASK_SIZE];

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

    private static void addTask(String taskType, String input) throws CamelException {
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

    private static void printList() throws CamelException {
        if (numberOfTasks == 0) {
            throw new CamelException(ErrorMessages.EMPTY_TASK_LIST);
        } else {
            NormalMessages.printTaskList(tasks, numberOfTasks);
        }
    }

    private static void markTask(String input) throws CamelException {
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

    private static void unmarkTask(String input) throws CamelException {
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

    private static boolean parseInput(String input) throws CamelException {
        String[] words = input.split(" ");
        switch (words[0]) {
        case "todo":
            try {
                addTask(words[0], words[1]);
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new CamelException(ErrorMessages.TODO_FORMAT);
            }
        case "deadline":
            //fallthrough
        case "event":
            int firstSpaceIndex = input.indexOf(' ');
            String item = input.substring(firstSpaceIndex + 1);
            addTask(words[0], item);
            break;
        case "list":
            printList();
            break;
        case "mark":
            try {
                markTask(words[1]);
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                throw new CamelException(ErrorMessages.MARK_FORMAT);
            }
            break;
        case "unmark":
            try {
                unmarkTask(words[1]);
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                throw new CamelException(ErrorMessages.UNMARK_FORMAT);
            }
            break;
        case "help":
            NormalMessages.printHelp();
            break;
        case "bye":
            NormalMessages.printGoodbye();
            return true;
        default:
            throw new CamelException(ErrorMessages.UNKNOWN_COMMAND);
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input;
        boolean hasEnded = false;

        NormalMessages.printHello();

        while (!hasEnded) {
            input = in.nextLine();
            try {
                hasEnded = parseInput(input);
            } catch (CamelException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
