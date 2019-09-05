package duke.task;

public class DukeTaskEvent extends DukeTask {

    private String taskLocation;

    /**
     * Constructor sets the Task description and the event location. The taskType is set to "E".
     *
     * @param taskName Task description of the {@link DukeTask}
     * @param taskLocation String containing the Task location.
     */
    public DukeTaskEvent(String taskName, String taskLocation) {
        super(taskName, "E");
        this.taskLocation = taskLocation;
    }

    /**
     * Constructor that takes in the task name followed by a boolean whether a task is complete, followed by the
     * task type. This constructor is called during the tasklist reconstruction phase when reading from the data file.
     *
     * @param taskName Task description of the {@link DukeTask}
     * @param isComplete if the task is complete or not.
     * @param taskLocation String containing the Task deadline in the required format.
     */
    public DukeTaskEvent(String taskName, boolean isComplete, String taskLocation) {
        super(taskName, isComplete, "E");
        this.taskLocation = taskLocation;
    }

    /**
     * Gets the location String of this event task.
     *
     * @return String containing the event.
     */
    public String getTaskLocation() {
        return this.taskLocation;
    }

    /**
     * Prints out this event Task in the format.
     * [E][x] taskName (at: taskLocation)
     *
     * @return The String formatted as above.
     */
    @Override
    public String toString() {
        assert !getTaskType().equals("");
        String symbol = getTaskIsComplete() ? "✓" : "✗";
        return "[" + getTaskType() + "][" + symbol + "] " + getTaskName() + " (at: " + this.taskLocation + ")";
    }
}
