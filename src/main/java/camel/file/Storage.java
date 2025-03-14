package camel.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import camel.exception.CamelException;
import camel.messages.ErrorMessages;
import camel.task.TaskList;

/**
 * Represents a file manager that can read, write and save data to disk.
 */
public class Storage {

    private final String address;
    private final TaskList tasks;

    public Storage(String address, TaskList tasks) {
        this.tasks = tasks;
        this.address = address;
    }

    /**
     * Opens a file based on the {@code address} as provided during the initialisation of this object. If the
     * directory to the file does not exist, the directory and file is created. If the file already exists, then data
     * from the file is read, parsed, and added into the TaskList {@code tasks}.
     */
    public void openFile() {
        Path dataPath = Paths.get(address);

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

    private void addTaskFromFile(String fileLine) throws CamelException {
        String[] parameters = fileLine.split(",");
        boolean isDone = Boolean.parseBoolean(parameters[1]);

        switch (parameters[0]) {
        case "T":
            tasks.addTodo(parameters[2], isDone);
            break;
        case "D":
            tasks.addDeadline(parameters[2], isDone, parameters[3]);
            break;
        case "E":
            tasks.addEvent(parameters[2], isDone, parameters[3], parameters[4]);
            break;
        default:
            throw new CamelException(ErrorMessages.fileCorrupted(fileLine));
        }
    }

    /**
     * Writes the formatted data from the TaskList into the file at {@code address}.
     */
    public void saveFile() {
        Path dataPath = Paths.get(address);
        try {
            Files.write(dataPath, tasks.fileFormat());
        } catch (IOException e) {
            System.out.println(ErrorMessages.saveFileFailed(e.getMessage()));
        }
    }
}
