package camel.messages;

import camel.task.Task;

import java.util.ArrayList;

public class NormalMessages {
    private static final String LINE_BREAK = "    ____________________________________________________________";

    public static void printHello() {
        System.out.println(LINE_BREAK + "\n\tHello! I'm Camel\n\tWhat can I do for you?\n" + LINE_BREAK);
    }

    public static void printGoodbye() {
        System.out.println(LINE_BREAK + "\n\tCamel says bye! Hope to see you again soon!\n" + LINE_BREAK);
    }

    public static void printHelp() {
        System.out.println(LINE_BREAK + "\n\tAvailable commands: {todo, deadline, event, list, mark, unmark, bye, " +
                "help}\n" + LINE_BREAK);
    }

    public static void printAddedTask(Task currentTask, int numberOfTasks) {
        System.out.println(LINE_BREAK + "\n\tCamel has added this task :)\n\t" + currentTask + "\n\tYou now have " +
                numberOfTasks + " task(s).\n" + LINE_BREAK);
    }

    public static void printTaskList(ArrayList<Task> tasks) {
        System.out.println(LINE_BREAK + "\n\tOf course! Camel shall gladly retrieve your tasks :)");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("\t%d.%s%n", i + 1, tasks.get(i));
        }
        System.out.println(LINE_BREAK);
    }

    public static void printMarkedAsDone(Task currentTask) {
        System.out.println(LINE_BREAK + "\n\tNice! Camel has marked this task as done :)\n\t" +
                currentTask + "\n" + LINE_BREAK);
    }

    public static void printMarkedAsNotDone(Task currentTask) {
        System.out.println(LINE_BREAK + "\n\tOk, Camel has marked this task as not done yet :)\n\t" +
                currentTask + "\n" + LINE_BREAK);
    }

    public static void printDeleteTask(Task currentTask) {
        System.out.println(LINE_BREAK + "\n\tOk, Camel has deleted this task :)\n\t" + currentTask + "\n" + LINE_BREAK);
    }
}
