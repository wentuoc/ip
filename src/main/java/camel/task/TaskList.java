package camel.task;

import camel.exception.CamelException;
import camel.messages.ErrorMessages;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTodo(String description, boolean isDone) {
        tasks.add(new Todo(description, isDone));
    }

    public void addDeadline(String description, boolean isDone, String doneBy) {
        tasks.add(new Deadline(description, isDone, doneBy));
    }

    public void addEvent(String description, boolean isDone, String startTime, String endTime) {
        tasks.add(new Event(description, isDone, startTime, endTime));
    }

    public Task markTask(int index) throws CamelException {
        if (tasks.isEmpty()) {
            throw new CamelException(ErrorMessages.EMPTY_TASK_LIST);
        }
        try {
            Task currentTask = tasks.get(index);
            if (currentTask.isDone()) {
                throw new CamelException(ErrorMessages.TASK_ALREADY_COMPLETED);
            } else {
                currentTask.setDone();
                return currentTask;
            }
        } catch (IndexOutOfBoundsException e) {
            throw new CamelException(ErrorMessages.taskIndexOutOfRange(tasks.size()));
        }
    }

    public Task unmarkTask(int index) throws CamelException {
        if (tasks.isEmpty()) {
            throw new CamelException(ErrorMessages.EMPTY_TASK_LIST);
        }
        try {
            Task currentTask = tasks.get(index);
            if (!currentTask.isDone()) {
                throw new CamelException(ErrorMessages.TASK_NOT_COMPLETED);
            } else {
                currentTask.setNotDone();
                return currentTask;
            }
        } catch (IndexOutOfBoundsException e) {
            throw new CamelException(ErrorMessages.taskIndexOutOfRange(tasks.size()));
        }
    }

    public Task deleteTask(int index) throws CamelException {
        if (tasks.isEmpty()) {
            throw new CamelException(ErrorMessages.EMPTY_TASK_LIST);
        }
        try {
            Task currentTask = tasks.get(index);
            tasks.remove(index);
            return currentTask;
        } catch (IndexOutOfBoundsException e) {
            throw new CamelException(ErrorMessages.taskIndexOutOfRange(tasks.size()));
        }
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTask(int index) throws CamelException {
        if (tasks.isEmpty()) {
            throw new CamelException(ErrorMessages.EMPTY_TASK_LIST);
        }
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new CamelException(ErrorMessages.taskIndexOutOfRange(tasks.size()));
        }
    }

    public ArrayList<String> fileFormat() {
        ArrayList<String> lines = new ArrayList<>();

        for (Task task : tasks) {
            lines.add(task.toFileFormat());
        }

        return lines;
    }
}
