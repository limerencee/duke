package duke.command;

import duke.util.DukeStorage;
import duke.util.DukeTaskList;
import duke.util.ui.DukeUi;
import duke.util.ui.DukeUiMessages;

/**
 * Clears the GUI output text box.
 */
public class DukeCommandClear extends DukeCommand {
    /**
     * @param tasks Instance of {@link DukeTaskList} which contains an existing list of {@link duke.task.DukeTask}.
     * @param ui Instance of {@link DukeUiMessages} which will show output to the user.
     * @param storage Instance of {@link DukeStorage} which will save the {@link DukeTaskList} to the hard disk.
     */
    @Override
    public void execute(DukeTaskList tasks, DukeUiMessages ui, DukeStorage storage) {
    }

    /**
     * Clears the text area in the GUI.
     *
     * @param gui Main GUI window with the text region to clear.
     */
    public void execute(DukeUi gui) {
        gui.clearTextRegion();
    }
}
