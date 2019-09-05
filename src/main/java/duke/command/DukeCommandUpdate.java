package duke.command;

import duke.command.update.DukeCommandUpdateTaskComplete;
import duke.command.update.DukeCommandUpdateDelete;
import duke.util.DukeStorage;
import duke.util.DukeTaskList;
import duke.util.ui.DukeUiMessages;

public class DukeCommandUpdate extends DukeCommand {

    protected String[] inputTokens;

    /**
     * Constructor that takes in the user input split by the " " delimiter into a String[].
     *
     * @param inputTokens User entered line split by a space delimiter.
     */
    public DukeCommandUpdate(String[] inputTokens) {
        this.inputTokens = inputTokens;
    }

    /**
     * This method will either update by marking a {@link duke.task.DukeTask} as done or deleting it, depending on the
     * command name.
     *
     * @param tasks Instance of {@link DukeTaskList} which contains an existing list of {@link duke.task.DukeTask}.
     * @param ui Instance of {@link DukeUiMessages} which will show output to the user.
     * @param storage Instance of {@link DukeStorage} which will save the {@link DukeTaskList} to the hard disk.
     */
    @Override
    public void execute(DukeTaskList tasks, DukeUiMessages ui, DukeStorage storage) {
        if (inputTokens.length == 2) {
            if (inputTokens[0].toLowerCase().equals("done")) {
                new DukeCommandUpdateTaskComplete(inputTokens).execute(tasks, ui, storage);
            } else if (inputTokens[0].toLowerCase().equals("delete")) {
                new DukeCommandUpdateDelete(inputTokens).execute(tasks, ui, storage);
            }
        } else {
            ui.displayMissingIndex();
        }
    }
}
