package camel.messages;

import camel.task.Task;

public class NormalMessages {
    public static final String LINE_BREAK = "    ____________________________________________________________";

    public static final String HELLO = LINE_BREAK + "\n\tHello! I'm Camel\n\tWhat can I do for you?\n" +
            LINE_BREAK;

    public static final String GOODBYE = LINE_BREAK + "\n\tCamel says bye! Hope to see you again soon!\n" +
            LINE_BREAK;

    public static final String HELP = LINE_BREAK + "\n\tAvailable commands: {todo, deadline, event, list, mark, " +
            "unmark, bye, help}\n" + LINE_BREAK;

    public static String addedTask(Task currentTask, int numberOfTasks) {
        return LINE_BREAK + "\n\tCamel has added this task :)\n\t" + currentTask + "\n\tYou now have " +
                numberOfTasks + " task(s).\n" + LINE_BREAK;
    }

    public static final String TASK_LIST_INTRO = LINE_BREAK + "\n\tOf course! Camel shall gladly retrieve your " +
            "tasks :)";

    public static String markedAsDone(Task currentTask) {
        return LINE_BREAK + "\n\tNice! Camel has marked this task as done :)\n\t" + currentTask + "\n" +
                LINE_BREAK;
    }

    public static String markedAsNotDone(Task currentTask) {
        return LINE_BREAK + "\n\tOk, Camel has marked this task as not done yet :)\n\t" + currentTask + "\n" +
                LINE_BREAK;
    }

    public static String deletedTask(Task currentTask) {
        return LINE_BREAK + "\n\tOk, Camel has deleted this task :)\n\t" + currentTask + "\n" + LINE_BREAK;
    }
}
