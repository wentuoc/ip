package camel.task;

import camel.exception.CamelException;
import camel.messages.ErrorMessages;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public int getSize() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Adds a new task object directly into the TaskList.
     *
     * @param task The Task object to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Adds a new Todo object into the TaskList by specifying its {@code description} and {@code isDone}.
     *
     * @param description The description of the Todo.
     * @param isDone Whether the Todo has been done.
     * @return The Todo object that was added into the TaskList.
     */
    public Task addTodo(String description, boolean isDone) {
        Todo newTodo = new Todo(description, isDone);
        tasks.add(newTodo);
        return newTodo;
    }

    /**
     * Adds a new Deadline object into the TaskList by specifying its {@code description}, {@code isDone}, and
     * {@code doneBy}.
     *
     * @param description The description of the Deadline.
     * @param isDone Whether the Deadline has been done.
     * @param doneBy The due date of the Deadline.
     * @return The Deadline object that was added into the TaskList.
     * @throws CamelException If an exception occurred when creating the Deadline object (e.g. wrong date time format).
     */
    public Task addDeadline(String description, boolean isDone, String doneBy) throws CamelException {
        Deadline newDeadline = new Deadline(description, isDone, doneBy);
        tasks.add(newDeadline);
        return newDeadline;
    }

    /**
     * Adds a new Event object into the TaskList by specifying its {@code description}, {@code isDone}, and
     * {@code startTime} and {@code endTime}.
     *
     * @param description The description of the Event.
     * @param isDone Whether the Event has been done.
     * @param startTime The start time of the Event.
     * @param endTime The end time of the Event.
     * @return The Event object that was added into the TaskList.
     * @throws CamelException If an exception occurred when creating the Event object (e.g. wrong date time format).
     */
    public Task addEvent(String description, boolean isDone, String startTime, String endTime) throws CamelException {
        Event newEvent = new Event(description, isDone, startTime, endTime);
        tasks.add(newEvent);
        return newEvent;
    }

    /**
     * Marks a task at a specified index as done.
     *
     * @param index The index of the task to be marked as done.
     * @return The Task object that was marked as done.
     * @throws CamelException If the Task has already been marked as done, or the specified index is out of bounds.
     */
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

    /**
     * Marks a task at a specified index as not done.
     *
     * @param index The index of the task to be marked as not done.
     * @return The Task object that was marked as not done.
     * @throws CamelException If the Task has already been marked as not done, or the specified index is out of bounds.
     */
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

    /**
     * Deletes a task at a specified index.
     *
     * @param index The index of the task to be deleted.
     * @return The Task object that was deleted.
     * @throws CamelException If the specified index is out of bounds, or if the TaskList is empty.
     */
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

    /**
     * Retrieves the task at a specified index.
     *
     * @param index The index of the task to be retrieved.
     * @return The Task object that was retrieved.
     * @throws CamelException If the specified index is out of bounds, or if the TaskList is empty.
     */
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

    /**
     * Finds all the tasks whose descriptions contain the input String.
     *
     * @param description The String to search for.
     * @return A new TaskList object containing all the Tasks that match the description.
     */
    public TaskList findTaskByDescription(String description) {
        TaskList matchingTasks = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().contains(description)) {
                matchingTasks.addTask(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Formats the TaskList into an ArrayList containing all attributes of a Task separated by commas,
     * in preparation for writing into memory.
     */
    public ArrayList<String> fileFormat() {
        ArrayList<String> lines = new ArrayList<>();

        for (Task task : tasks) {
            lines.add(task.toFileFormat());
        }

        return lines;
    }
}
