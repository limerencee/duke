package duke.command.add;

import duke.command.DukeCommandAdd;
import duke.task.DukeTaskEvent;
import duke.util.DukeParser;
import duke.util.DukeStorage;
import duke.util.DukeTaskList;
import duke.util.ui.DukeUiMessages;

public class DukeCommandAddEvent extends DukeCommandAdd {

    /**
     * Constructor that takes in the user input split by the " " delimiter into a String[].
     *
     * @param inputTokens User entered line split by a space delimiter.
     */
    public DukeCommandAddEvent(String[] inputTokens) {
        super(inputTokens);
    }

    /**
     * This method will handle adding of a {@link DukeTaskEvent} into {@link DukeTaskList} list of
     * {@link duke.task.DukeTask}.
     *
     * @param tasks Instance of {@link DukeTaskList} which contains an existing list of {@link duke.task.DukeTask}.
     * @param ui Instance of {@link DukeUiMessages} which will show output to the user.
     * @param storage Instance of {@link DukeStorage} which will save the {@link DukeTaskList} to the hard disk.
     */
    @Override
    public void execute(DukeTaskList tasks, DukeUiMessages ui, DukeStorage storage) {
        int eventParameterIndex = DukeParser.getInputFlagParameterStartingIndex(inputTokens, "/at");
        if (eventParameterIndex == -1 || eventParameterIndex == inputTokens.length) {
            ui.displayMissingEventParam();
        } else {
            assert eventParameterIndex > 2; //minimally: 'event name /at (argv)'
            String eventTaskName = DukeParser.concatStringTokens(inputTokens, 1,
                    (eventParameterIndex - 2));
            String eventParameterString = DukeParser.concatStringTokens(inputTokens, eventParameterIndex,
                    (inputTokens.length - 1));
            DukeTaskEvent dukeEvent = new DukeTaskEvent(eventTaskName, eventParameterString);
            tasks.addToDukeTasks(dukeEvent, ui, storage);
        }
    }
}
