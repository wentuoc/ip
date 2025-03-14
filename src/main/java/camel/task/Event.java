package camel.task;

import camel.exception.CamelException;

import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Event(String description, boolean isDone, String startTime, String endTime) throws CamelException {
        super(description, isDone);
        this.startTime = convertDateTime(startTime);
        this.endTime = convertDateTime(endTime);
    }

    public String getStartTime() {
        return printDateTime(startTime);
    }

    public void setStartTime(String startTime) throws CamelException {
        this.startTime = convertDateTime(startTime);
    }

    public String getEndTime() {
        return printDateTime(endTime);
    }

    public void setEndTime(String endTime) throws CamelException {
        this.endTime = convertDateTime(endTime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getStartTime() + " to: " + getEndTime() + ")";
    }

    @Override
    public String toFileFormat() {
        return "E," + super.toFileFormat() + "," + getStartTime() + "," + getEndTime();
    }
}
