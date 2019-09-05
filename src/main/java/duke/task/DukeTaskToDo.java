package duke.task;

public class DukeTaskToDo extends DukeTask {

    /**
     * Constructor sets the Task description. The taskType is set to "T".
     *
     * @param taskName Task description of the {@link DukeTask}
     */
    public DukeTaskToDo(String taskName) {
        super(taskName, "T");
    }

    /**
     * Constructor that takes in the task name followed by a boolean whether a task is complete.
     * This constructor is called during the tasklist reconstruction phase when reading from the data file.
     *
     * @param taskName Task description of the {@link DukeTask}
     * @param isComplete if the task is complete or not.
     */
    public DukeTaskToDo(String taskName, boolean isComplete) {
        super(taskName, isComplete, "T");
    }

    /**
     * Prints out this to-do Task in the format.
     * [T][x] taskName
     *
     * @return The String formatted as above.
     */
    @Override
    public String toString() {
        assert !getTaskType().equals("");
        String symbol = getTaskIsComplete() ? "✓" : "✗";
        return "[" + getTaskType() + "][" + symbol + "] " + getTaskName();
    }
}
