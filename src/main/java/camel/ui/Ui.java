package camel.ui;

import camel.exception.CamelException;
import camel.messages.NormalMessages;
import camel.task.Task;
import camel.task.TaskList;

import java.util.Scanner;

public class Ui {

    private Scanner in = new Scanner(System.in);

    public String getInput() {
        return in.nextLine();
    }

    public void printHello() {
        System.out.println(NormalMessages.HELLO);
    }

    public void printGoodbye() {
        System.out.println(NormalMessages.GOODBYE);
    }

    public void printHelp() {
        System.out.println(NormalMessages.HELP);
    }

    public void printAddedTask(Task currentTask, int numberOfTasks) {
        System.out.println(NormalMessages.addedTask(currentTask, numberOfTasks));
    }

    public void printTaskList(TaskList tasks) throws CamelException {
        System.out.println(NormalMessages.TASK_LIST_INTRO);
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.printf("\t%d.%s%n", i + 1, tasks.getTask(i));
        }
        System.out.println(NormalMessages.LINE_BREAK);
    }

    public void printMarkedAsDone(Task currentTask) {
        System.out.println(NormalMessages.markedAsDone(currentTask));
    }

    public void printMarkedAsNotDone(Task currentTask) {
        System.out.println(NormalMessages.markedAsNotDone(currentTask));
    }

    public void printDeletedTask(Task currentTask) {
        System.out.println(NormalMessages.deletedTask(currentTask));
    }
}
