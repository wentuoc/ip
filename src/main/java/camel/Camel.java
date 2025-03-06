package camel;


import camel.commands.AbstractCommand;
import camel.exception.*;
import camel.file.Storage;
import camel.ui.Parser;
import camel.ui.Ui;
import camel.task.TaskList;

public class Camel {
    private static TaskList tasks;
    private static Storage storage;
    private static Parser parser;
    private static Ui userInterface;
    private static final String ADDRESS = "data/camel.txt";

    private static void initialiseCamel() {
        tasks = new TaskList();
        storage = new Storage(ADDRESS, tasks);
        parser = new Parser();
        userInterface = new Ui();

        storage.openFile();
        userInterface.printHello();
    }

    private static void runCamelUntilExit() {
        boolean hasEnded = false;
        do {
            try {
                String userInput = userInterface.getInput();
                AbstractCommand c = parser.parseInput(userInput);
                c.execute(tasks, userInterface, storage);
                hasEnded = c.hasEnded();
            } catch (CamelException e) {
                userInterface.handleException(e);
            }
        } while (!hasEnded);
    }

    private static void stopCamel() {
        storage.saveFile();
        userInterface.printGoodbye();
    }

    public static void main(String[] args) {
        initialiseCamel();
        runCamelUntilExit();
        stopCamel();
    }
}
