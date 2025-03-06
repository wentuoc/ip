package camel.commands;

import camel.file.Storage;
import camel.task.Task;
import camel.task.TaskList;
import camel.ui.Ui;

public class AddEventCommand extends AddTaskCommand {

    private final String startTime;
    private final String endTime;

    public AddEventCommand(String description, boolean isDone, String startTime, String endTime) {
        super(description, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public void execute(TaskList tasks, Ui userInterface, Storage storage) {
        Task newEvent = tasks.addEvent(description, isDone, startTime, endTime);
        printAcknowledgement(userInterface, newEvent, tasks);
    }
}
