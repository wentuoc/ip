import java.util.Scanner;

public class Camel {
    private static int numberOfTasks = 0;
    private static String[] tasks = new String[100];

    private static void addToList(String input) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Camel has added: " + input + " :)");
        System.out.println("    ____________________________________________________________");
        tasks[numberOfTasks] = input;
        numberOfTasks++;
    }

    private static void printList() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Of course! Camel shall gladly retrieve your tasks :)");
        for (int i = 0; i < numberOfTasks; i++) {
            System.out.printf("    %d. %s%n", i+1, tasks[i]);
        }
        System.out.println("    ____________________________________________________________");
    }

    private static void exitLoop() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Camel says: Bye. Hope to see you again soon!");
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
            case "bye":
                exitLoop();
                hasEnded = true;
                break;
            default:
                addToList(input);
                break;
            }
        }
    }
}
