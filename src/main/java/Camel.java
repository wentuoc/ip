import java.util.Scanner;

public class Camel {
    private static final String LINE_BREAK = "    ____________________________________________________________";
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
                throw new CamelException("I don't understand :( Please ensure the format is as follows: \"deadline " +
                        "<name> /by <time>\"");
            }
        case "event":
            try {
                String[] eventInputs = decodeEventInput(input);
                newTask = new Event(eventInputs[0], eventInputs[1], eventInputs[2]);
                tasks[numberOfTasks] = newTask;
                break;
            } catch (StringIndexOutOfBoundsException e) {
                throw new CamelException("I don't understand :( Please ensure the format is as follows: \"event " +
                        "<name> /from <time> /to <time>\"");
            }
        default:
            break;
        }

        System.out.println(LINE_BREAK);
        System.out.println("    Camel has added this task :)");
        System.out.println("    " + tasks[numberOfTasks]);
        System.out.println("    You now have " + (numberOfTasks + 1) + " task(s).");
        System.out.println(LINE_BREAK);

        numberOfTasks++;
    }

    private static void printList() throws CamelException {
        if (numberOfTasks == 0) {
            throw new CamelException("Task list is currently empty. Add a new task to start slaying your goals :)");
        } else {
            System.out.println(LINE_BREAK);
            System.out.println("    Of course! Camel shall gladly retrieve your tasks :)");
            for (int i = 0; i < numberOfTasks; i++) {
                Task currentTask = tasks[i];
                System.out.printf("    %d.%s%n", i + 1, currentTask);
            }
            System.out.println(LINE_BREAK);
        }
    }

    private static void markTask(String input) throws CamelException {
        int index = Integer.parseInt(input);
        if (index >= 1 && index <= numberOfTasks) {
            Task currentTask = tasks[index - 1];

            if (currentTask.isDone()) {
                throw new CamelException("You have already completed this task. Yay! :)");
            } else {
                currentTask.setDone();
                System.out.println(LINE_BREAK);
                System.out.println("    Nice! Camel has marked this task as done :)");
                System.out.println("    " + currentTask);
                System.out.println(LINE_BREAK);
            }
        }
        else if (numberOfTasks == 0) {
            throw new CamelException("Task list is currently empty. Add a new task to start slaying your goals :)");
        }
        else {
            throw new CamelException(String.format("Task index is out of range :( Please select within 1 and %d only",
                    numberOfTasks));
        }
    }

    private static void unmarkTask(String input) throws CamelException {
        int index = Integer.parseInt(input);
        if (index >= 1 && index <= numberOfTasks) {
            Task currentTask = tasks[index - 1];

            if (!currentTask.isDone()) {
                throw new CamelException("You have already not completed this task :(");
            } else {
                currentTask.setNotDone();
                System.out.println(LINE_BREAK);
                System.out.println("    Ok, Camel has marked this task as not done yet :)");
                System.out.println("    " + currentTask);
                System.out.println(LINE_BREAK);
            }
        }
        else if (numberOfTasks == 0) {
            throw new CamelException("Task list is currently empty. Add a new task to start slaying your goals :)");
        }
        else {
            throw new CamelException(String.format("Task index is out of range :( Please select within 1 and %d " +
                            "only", numberOfTasks));
        }
    }

    private static void printExitLoop() {
        System.out.println(LINE_BREAK);
        System.out.println("    Camel says bye! Hope to see you again soon!");
        System.out.println(LINE_BREAK);
    }

    private static boolean parseInput(String input) throws CamelException {
        String[] words = input.split(" ");
        switch (words[0]) {
        case "todo":
            try {
                addTask(words[0], words[1]);
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new CamelException("I don't understand :( Please ensure the format is as follows: \"todo " +
                        "<name>\"");
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
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new CamelException("I don't understand :( Please ensure the format is as follows: \"mark " +
                        "<task index>\"");
            }
            break;
        case "unmark":
            try {
                unmarkTask(words[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new CamelException("I don't understand :( Please ensure the format is as follows: \"unmark " +
                        "<name>\"");
            }
            break;
        case "bye":
            printExitLoop();
            return true;
        default:
            throw new CamelException("I don't understand your command :( Please choose from this list only: {todo, deadline, " +
                    "event, list, mark, unmark, bye}");
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input;
        boolean hasEnded = false;

        System.out.println(LINE_BREAK);
        System.out.println("    Hello! I'm Camel");
        System.out.println("    What can I do for you?");
        System.out.println(LINE_BREAK);

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
