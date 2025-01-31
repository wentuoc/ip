import java.util.Scanner;

public class Camel {
    public static void echoInput(String input) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Camel says: \"" + input + "\" back :)");
        System.out.println("    ____________________________________________________________");
    }

    public static void exitLoop() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Camel says: Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input;

        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Camel");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");

        while (true) {
            input = in.nextLine();
            if (!input.equals("bye")) {
                echoInput(input);
            } else {
                exitLoop();
                break;
            }
        }
    }
}
