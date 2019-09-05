package duke.task;

public class DukeTask {

    private String taskName;
    private String taskType;
    private boolean isComplete;

    /**
     * Constructor that takes in the task name followed by the task type.
     *
     * @param taskName Task description of the {@link DukeTask}.
     * @param taskType Type of task.
     */
    public DukeTask(String taskName, String taskType) {
        this.taskName = taskName;
        this.taskType = taskType;
        this.isComplete = false;
    }

    /**
     * Constructor that takes in the task name followed by a boolean whether a task is complete, followed by the
     * task type. This constructor is called during the tasklist reconstruction phase when reading from the data file.
     *
     * @param taskName Task description of the {@link DukeTask}.
     * @param isComplete if the task is complete or not.
     * @param taskType Type of task.
     */
    public DukeTask(String taskName, boolean isComplete, String taskType) {
        this.taskName = taskName;
        this.isComplete = isComplete;
        this.taskType = taskType;
    }

    /**
     * Gets the Task description of this {@link DukeTask}.
     *
     * @return String containing the Task description.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Gets the Task type of this {@link DukeTask}.
     *
     * @return String containing the type of Task.
     */
    public String getTaskType() {
        return this.taskType;
    }

    /**
     * Gets whether the Task is complete.
     *
     * @return boolean stating if the Task is completed.
     */
    public boolean getTaskIsComplete() {
        return this.isComplete;
    }

    /**
     * Sets this Task as complete. This is done by setting {@link DukeTask#isComplete} to true.
     */
    public void setTaskComplete() {
        this.isComplete = true;
    }
}
