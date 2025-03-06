package camel.commands;

import camel.exception.CamelException;
import camel.messages.ErrorMessages;
import camel.task.Task;
import camel.task.TaskList;
import camel.ui.Ui;


public class Commands {

    TaskList tasks;
    Ui userInterface;

    public Commands(TaskList tasks) {
        this.tasks = tasks;
        userInterface = new Ui();
    }

    public Task addTodo(String description, boolean isDone) throws CamelException {
        tasks.addTodo(description, isDone);
        return tasks.getTask(tasks.getSize() - 1);
    }

    public Task addDeadline(String description, boolean isDone, String doneBy) throws CamelException {
        tasks.addDeadline(description, isDone, doneBy);
        return tasks.getTask(tasks.getSize() - 1);
    }

    public Task addEvent(String description, boolean isDone, String startTime, String endTime) throws CamelException {
        tasks.addEvent(description, isDone, startTime, endTime);
        return tasks.getTask(tasks.getSize() - 1);
    }

    public void printList() throws CamelException {
        if (tasks.getSize() == 0) {
            throw new CamelException(ErrorMessages.EMPTY_TASK_LIST);
        }
        userInterface.printTaskList(tasks);
    }

    public Task mark(int index) throws CamelException {
        tasks.markTask(index);
        return tasks.getTask(index);
    }

    public Task unmark(int index) throws CamelException {
        tasks.unmarkTask(index);
        return tasks.getTask(index);
    }

    public Task delete(int index) throws CamelException {
        Task currentTask = tasks.getTask(index);
        tasks.deleteTask(index);
        return currentTask;
    }

    public void printHelp() {
        userInterface.printHelp();
    }
}
