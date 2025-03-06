package camel;


import camel.exception.*;
import camel.file.Storage;
import camel.ui.Parser;
import camel.ui.Ui;
import camel.task.TaskList;

public class Camel {
    private static TaskList tasks;
    private static Storage fileHandler;
    private static Parser parser;
    private static Ui userInterface;
    private static final String ADDRESS = "data/camel.txt";

    private static void initialiseCamel() {
        tasks = new TaskList();
        fileHandler = new Storage(ADDRESS, tasks);
        parser = new Parser(tasks);
        userInterface = new Ui();

        fileHandler.openFile();
        userInterface.printHello();
    }

    private static void runCamelUntilExit() {
        boolean hasEnded = false;
        do {
            String userInput = userInterface.getInput();
            try {
                hasEnded = parser.parseInput(userInput);
            } catch (CamelException e) {
                System.out.println(e.getMessage());
            }
        } while (!hasEnded);
    }

    private static void stopCamel() {
        fileHandler.saveFile();
        userInterface.printGoodbye();
    }

    public static void main(String[] args) {
        initialiseCamel();
        runCamelUntilExit();
        stopCamel();
    }
}
