package camel.ui;

import camel.exception.CamelException;
import camel.messages.NormalMessages;
import camel.task.Task;
import camel.task.TaskList;

import java.util.Scanner;

/**
 * Represents the user interface. It fetches inputs from the user and prints messages to the console.
 */
public class Ui {

    private Scanner in = new Scanner(System.in);

    /**
     *
     * @return Input from the scanner.
     */
    public String getInput() {
        return in.nextLine();
    }

    /**
     * Prints the hello message.
     */
    public void printHello() {
        System.out.println(NormalMessages.HELLO);
    }

    /**
     * Prints the goodbye message.
     */
    public void printGoodbye() {
        System.out.println(NormalMessages.GOODBYE);
    }

    /**
     * Prints the help message.
     */
    public void printHelp() {
        System.out.println(NormalMessages.HELP);
    }

    /**
     * Prints the task added message.
     *
     * @param currentTask The task that was added.
     * @param numberOfTasks The new number of tasks.
     */
    public void printAddedTask(Task currentTask, int numberOfTasks) {
        System.out.println(NormalMessages.addedTask(currentTask, numberOfTasks));
    }

    /**
     * Prints the list of tasks.
     *
     * @param tasks A TaskList object representing the list of tasks
     * @throws CamelException If the task list is empty.
     */
    public void printTaskList(TaskList tasks) throws CamelException {
        System.out.println(NormalMessages.TASK_LIST_INTRO);
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.printf("\t%d.%s%n", i + 1, tasks.getTask(i));
        }
        System.out.println(NormalMessages.LINE_BREAK);
    }

    /**
     * Prints the marked as done message.
     *
     * @param currentTask The task that was marked as done.
     */
    public void printMarkedAsDone(Task currentTask) {
        System.out.println(NormalMessages.markedAsDone(currentTask));
    }

    /**
     * Prints the marked as not done message.
     *
     * @param currentTask The task that was marked as not done.
     */
    public void printMarkedAsNotDone(Task currentTask) {
        System.out.println(NormalMessages.markedAsNotDone(currentTask));
    }

    /**
     * Prints the deleted task message.
     *
     * @param currentTask The task that was deleted.
     */
    public void printDeletedTask(Task currentTask) {
        System.out.println(NormalMessages.deletedTask(currentTask));
    }

    /**
     * Prints the error message associated with the exception that occurred.
     *
     * @param e The exception that occurred
     */
    public void handleException(Exception e) {
        System.out.println(e.getMessage());
    }
}
