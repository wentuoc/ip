package camel.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import camel.commands.Commands;
import camel.exception.CamelException;
import camel.messages.ErrorMessages;

public class FileHandling {

    public static void openFile() {
        Path dataPath = Paths.get("data/camel.txt");

        if (Files.exists(dataPath)) {
            try {
                List<String> data = Files.readAllLines(dataPath);
                for (String line : data) {
                    addTaskFromFile(line);
                }
            } catch (IOException | CamelException e) {
                System.out.println(ErrorMessages.fileCorrupted(e.getMessage()));
            }
        } else {
            try {
                Files.createDirectory(Paths.get("data"));
                Files.createFile(dataPath);
            } catch (IOException e) {
                System.out.println(ErrorMessages.createFileFailed(e.getMessage()));
            }
        }
    }

    private static void addTaskFromFile(String fileLine) throws CamelException {
        String[] parameters = fileLine.split(",");
        boolean isDone = Boolean.parseBoolean(parameters[1]);

        switch (parameters[0]) {
        case "T":
            Commands.addTodo(parameters[2], isDone);
            break;
        case "D":
            Commands.addDeadline(parameters[2], isDone, parameters[3]);
            break;
        case "E":
            Commands.addEvent(parameters[2], isDone, parameters[3], parameters[4]);
            break;
        default:
            throw new CamelException(ErrorMessages.fileCorrupted(fileLine));
        }
    }

    public static void saveFile() {
        Path dataPath = Paths.get("data/camel.txt");
        try {
            Files.write(dataPath, Commands.fileFormat());
        } catch (IOException e) {
            System.out.println(ErrorMessages.saveFileFailed(e.getMessage()));
        }
    }
}
