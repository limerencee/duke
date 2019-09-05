package duke.command;

import duke.command.list.DukeCommandListAll;
import duke.command.list.DukeCommandListFind;
import duke.command.list.DukeCommandListReminders;
import duke.util.DukeStorage;
import duke.util.DukeTaskList;
import duke.util.ui.DukeUiMessages;

public class DukeCommandList extends DukeCommand {

    protected String[] inputTokens;

    public DukeCommandList(String[] inputTokens) {
        this.inputTokens = inputTokens;
    }

    /**
     * This method will list the current existing {@link duke.task.DukeTask} from {@link DukeTaskList}.
     *
     * @param tasks Instance of {@link DukeTaskList} which contains an existing list of {@link duke.task.DukeTask}.
     * @param ui Instance of {@link DukeUiMessages} which will show output to the user.
     * @param storage Instance of {@link DukeStorage} which will save the {@link DukeTaskList} to the hard disk.
     */
    @Override
    public void execute(DukeTaskList tasks, DukeUiMessages ui, DukeStorage storage) {
        if (inputTokens[0].toLowerCase().equals("list")) {
            new DukeCommandListAll(inputTokens).execute(tasks, ui, storage);
        } else if (inputTokens[0].toLowerCase().equals("find")) {
            new DukeCommandListFind(inputTokens).execute(tasks, ui, storage);
        } else if (inputTokens[0].toLowerCase().equals("reminders")) {
            new DukeCommandListReminders(inputTokens).execute(tasks, ui, storage);
        }
    }
}
