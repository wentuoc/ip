import java.util.Scanner;

public class Camel {
    private static int numberOfTasks = 0;
    private static Task[] tasks = new Task[100];

    private static String[] decodeDeadlineInput(String input) {
        int slashIndex = input.indexOf('/');
        String description = input.substring(0, slashIndex);
        String dueDate = input.substring(slashIndex + 1);
        return new String[]{description, dueDate};
    }

    private static String[] decodeEventInput(String input) {
        int firstSlashIndex = input.indexOf('/');
        int secondSlashIndex = input.indexOf('/', firstSlashIndex + 1);
        String description = input.substring(0, firstSlashIndex);
        String fromDate = input.substring(firstSlashIndex + 1, secondSlashIndex);
        String toDate = input.substring(secondSlashIndex + 1);
        return new String[]{description, fromDate, toDate};
    }

    private static void addTask(String taskType, String input) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Camel has added: " + input + " :)");
        System.out.println("    ____________________________________________________________");
        Task newTask;
        
        switch (taskType) {
        case "todo":
            newTask = new Todo(input);
            tasks[numberOfTasks] = newTask;
            break;
        case "deadline":
            String[] deadlineInputs = decodeDeadlineInput(input);
            newTask = new Deadline(deadlineInputs[0], deadlineInputs[1]);
            tasks[numberOfTasks] = newTask;
            break;
        case "event":
            String[] eventInputs = decodeEventInput(input);
            newTask = new Event(eventInputs[0], eventInputs[1], eventInputs[2]);
            tasks[numberOfTasks] = newTask;
            break;
        default:
            break;
        }
        numberOfTasks++;
    }

    private static void printList() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Of course! Camel shall gladly retrieve your tasks :)");
        for (int i = 0; i < numberOfTasks; i++) {
            Task currentTask = tasks[i];
            System.out.printf("    %d.", i + 1);
            currentTask.printTask();
        }
        System.out.println("    ____________________________________________________________");
    }

    private static void markTask(String input) {
        int index = Integer.parseInt(input);
        if (index >= 1 && index <= numberOfTasks) {
            Task currentTask = tasks[index-1];
            currentTask.setDone();
            System.out.println("    ____________________________________________________________");
            System.out.println("    Nice! Camel has marked this task as done :)");
            System.out.print("    ");
            currentTask.printTask();
            System.out.println("    ____________________________________________________________");
        }
    }

    private static void unmarkTask(String input) {
        int index = Integer.parseInt(input);
        if (index >= 1 && index <= numberOfTasks) {
            Task currentTask = tasks[index-1];
            currentTask.setNotDone();
            System.out.println("    ____________________________________________________________");
            System.out.println("    Ok, Camel has marked this task as not done yet :)");
            System.out.print("    ");
            currentTask.printTask();
            System.out.println("    ____________________________________________________________");
        }
    }

    private static void exitLoop() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Camel says bye! Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    private static boolean parseInput(String input) {
        String[] words = input.split(" ");
        switch (words[0]) {
        case "todo":
        case "deadline":
        case "event":
            int firstSpaceIndex = input.indexOf(' ');
            String item = input.substring(firstSpaceIndex+1);
            addTask(words[0], item);
            break;
        case "list":
            printList();
            break;
        case "mark":
            markTask(words[1]);
            break;
        case "unmark":
            unmarkTask(words[1]);
            break;
        case "bye":
            exitLoop();
            return true;
        default:
            //TODO: Exception handling for unrecognised commands
            break;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input;
        boolean hasEnded = false;

        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Camel");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");

        while (!hasEnded) {
            input = in.nextLine();
            hasEnded = parseInput(input);
        }
    }
}
