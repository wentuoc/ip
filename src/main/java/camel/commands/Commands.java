package camel.commands;

import java.util.ArrayList;

import camel.exception.CamelException;
import camel.messages.*;
import camel.task.*;


public class Commands {

    TaskList tasks;

    public Commands(TaskList tasks) {
        this.tasks = tasks;
    }

    public void addTodo(String description, boolean isDone) {
        tasks.addTodo(description, isDone);
    }

    public void addDeadline(String description, boolean isDone, String doneBy) {
        tasks.addDeadline(description, isDone, doneBy);
    }

    public void addEvent(String description, boolean isDone, String startTime, String endTime) {
        tasks.addEvent(description, isDone, startTime, endTime);
    }


    public TaskList print() throws CamelException {
        return tasks;
    }

    public void mark(String input) throws CamelException {
        int index = Integer.parseInt(input); //NumberFormatException
        tasks.markTask(index);
    }

    public void unmark(String input) throws CamelException {
        int index = Integer.parseInt(input);
        tasks.unmarkTask(index);
    }

    public void delete(String input) throws CamelException {
        int index = Integer.parseInt(input);
        tasks.deleteTask(index);
    }
}
