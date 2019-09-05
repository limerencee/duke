package duke.command;

import duke.command.add.DukeCommandAddDeadline;
import duke.command.add.DukeCommandAddEvent;
import duke.command.add.DukeCommandAddTodo;
import duke.util.DukeStorage;
import duke.util.DukeTaskList;
import duke.util.ui.DukeUiMessages;

public class DukeCommandAdd extends DukeCommand {

    protected String[] inputTokens;

    /**
     * Constructor that takes in the user input split by the " " delimiter into a String[].
     *
     * @param inputTokens User entered line split by a space delimiter.
     */
    public DukeCommandAdd(String[] inputTokens) {
        this.inputTokens = inputTokens;
    }

    /**
     * This method will add {@link duke.task.DukeTask} according to the command name.
     *
     * @param tasks Instance of {@link DukeTaskList} which contains an existing list of {@link duke.task.DukeTask}.
     * @param ui Instance of {@link DukeUiMessages} which will show output to the user.
     * @param storage Instance of {@link DukeStorage} which will save the {@link DukeTaskList} to the hard disk.
     */
    @Override
    public void execute(DukeTaskList tasks, DukeUiMessages ui, DukeStorage storage) {
        if (inputTokens.length == 1) {
            ui.displayEmptyDescriptionError();
        } else {
            if (inputTokens[0].toLowerCase().equals("todo")) {
                new DukeCommandAddTodo(inputTokens).execute(tasks, ui, storage);
            } else if (inputTokens[0].toLowerCase().equals("deadline")) {
                new DukeCommandAddDeadline(inputTokens).execute(tasks, ui, storage);
            } else if (inputTokens[0].toLowerCase().equals("event")) {
                new DukeCommandAddEvent(inputTokens).execute(tasks, ui, storage);
            }
        }
    }
}
