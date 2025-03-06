package camel.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import camel.commands.Commands;
import camel.exception.CamelException;
import camel.messages.ErrorMessages;
import camel.task.TaskList;

public class Storage {

    private final Commands commands;
    private final String address;
    private final TaskList tasks;

    public Storage(String address, TaskList tasks) {
        commands = new Commands(tasks);
        this.tasks = tasks;
        this.address = address;
    }

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
            commands.addTodo(parameters[2], isDone);
            break;
        case "D":
            commands.addDeadline(parameters[2], isDone, parameters[3]);
            break;
        case "E":
            commands.addEvent(parameters[2], isDone, parameters[3], parameters[4]);
            break;
        default:
            throw new CamelException(ErrorMessages.fileCorrupted(fileLine));
        }
    }

    public void saveFile() {
        Path dataPath = Paths.get(address);
        try {
            Files.write(dataPath, tasks.fileFormat());
        } catch (IOException e) {
            System.out.println(ErrorMessages.saveFileFailed(e.getMessage()));
        }
    }
}
