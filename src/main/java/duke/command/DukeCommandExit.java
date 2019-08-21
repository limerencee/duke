package duke.command;

import duke.util.DukeStorage;
import duke.util.DukeTaskList;
import duke.util.DukeUi;

public class DukeCommandExit extends DukeCommand {

    /**
     * This method will exit the application after executing the exit message from
     * {@link DukeUi#displayTerminateMessage()}.
     * @param tasks Instance of {@link DukeTaskList} which contains an existing list of {@link duke.task.DukeTask}.
     * @param ui Instance of {@link DukeUi} which will show output to the user.
     * @param storage Instance of {@link DukeStorage} which will save the {@link DukeTaskList} to the hard disk.
     */
    @Override
    public void execute(DukeTaskList tasks, DukeUi ui, DukeStorage storage) {
        ui.displayTerminateMessage();
        System.exit(0);
    }
}
