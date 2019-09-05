package duke.command;

import duke.util.DukeStorage;
import duke.util.DukeTaskList;
import duke.util.ui.DukeUiMessages;

public abstract class DukeCommand {
    /**
     * Abstract method that must be implemented in all implementing subclasses.
     *
     * @param tasks Instance of {@link DukeTaskList} which contains an existing list of {@link duke.task.DukeTask}.
     * @param ui Instance of {@link DukeUiMessages} which will show output to the user.
     * @param storage Instance of {@link DukeStorage} which will save the {@link DukeTaskList} to the hard disk.
     */
    public abstract void execute(DukeTaskList tasks, DukeUiMessages ui, DukeStorage storage);
}
