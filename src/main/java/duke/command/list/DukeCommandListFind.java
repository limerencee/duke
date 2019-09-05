package duke.command.list;

import duke.command.DukeCommandList;
import duke.util.DukeParser;
import duke.util.DukeStorage;
import duke.util.DukeTaskList;
import duke.util.ui.DukeUiMessages;

public class DukeCommandListFind extends DukeCommandList {

    /**
     * Constructor that takes in the user input split by the " " delimiter into a String[].
     *
     * @param inputTokens User entered line split by a space delimiter.
     */
    public DukeCommandListFind(String[] inputTokens) {
        super(inputTokens);
    }

    /**
     * Calls the method to find and display user tasks according to the specified search terms.
     *
     * @param tasks Instance of {@link DukeTaskList} which contains an existing list of {@link duke.task.DukeTask}.
     * @param ui Instance of {@link DukeUiMessages} which will show output to the user.
     * @param storage Instance of {@link DukeStorage} which will save the {@link DukeTaskList} to the hard disk.
     */
    @Override
    public void execute(DukeTaskList tasks, DukeUiMessages ui, DukeStorage storage) {
        if (inputTokens.length == 1) {
            ui.displayEmptySearchTermError();
        } else {
            assert inputTokens.length > 1;
            String searchTerms = DukeParser.concatStringTokens(inputTokens, 1, (inputTokens.length - 1));
            tasks.findDukeTasks(searchTerms, ui);
        }
    }
}
