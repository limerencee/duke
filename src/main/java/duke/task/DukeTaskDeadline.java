package duke.task;

public class DukeTaskDeadline extends DukeTask {

    private String taskDeadline;

    /**
     * Constructor sets the Task description and the deadline. The deadline must be formatted like: "d/m/yyyy HHmm"
     * The taskType is set to "D".
     *
     * @param taskName Task description of the {@link DukeTask}
     * @param taskDeadline String containing the Task deadline in the required format.
     */
    public DukeTaskDeadline(String taskName, String taskDeadline) {
        super(taskName, "D");
        this.taskDeadline = taskDeadline;
    }

    /**
     * Constructor that takes in the task name followed by a boolean whether a task is complete, followed by the
     * task type. This constructor is called during the tasklist reconstruction phase when reading from the data file.
     *
     * @param taskName Task description of the {@link DukeTask}
     * @param isComplete if the task is complete or not.
     * @param taskDeadline String containing the Task deadline in the required format.
     */
    public DukeTaskDeadline(String taskName, boolean isComplete, String taskDeadline) {
        super(taskName, isComplete, "D");
        this.taskDeadline = taskDeadline;
    }

    /**
     * Gets the deadline String of this deadline task.
     *
     * @return String containing the deadline in the format "d/m/yyyy HHmm".
     */
    public String getTaskDeadline() {
        return this.taskDeadline;
    }

    /**
     * Prints out this deadline Task in the format.
     * [D][x] taskName (by: taskDeadline)
     *
     * @return The String formatted as above.
     */
    @Override
    public String toString() {
        assert !getTaskType().equals("");
        String symbol = getTaskIsComplete() ? "✓" : "✗";
        return "[" + getTaskType() + "][" + symbol + "] " + getTaskName() + " (by: " + this.taskDeadline + ")";
    }

}
