import java.util.Scanner;

public class Camel {
    private static int numberOfTasks = 0;
    private static Task[] tasks = new Task[100];

    private static void addTask(String input) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Camel has added: " + input + " :)");
        System.out.println("    ____________________________________________________________");
        Task newTask = new Task(input);
        tasks[numberOfTasks] = newTask;
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
            String[] words = input.split(" ");
            switch (words[0]) {
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
                hasEnded = true;
                break;
            default:
                addTask(input);
                break;
            }
        }
    }
}
