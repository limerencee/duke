package duke.command.add;

import duke.command.DukeCommandAdd;
import duke.task.DukeTaskToDo;
import duke.util.DukeParser;
import duke.util.DukeStorage;
import duke.util.DukeTaskList;
import duke.util.ui.DukeUiMessages;

public class DukeCommandAddTodo extends DukeCommandAdd {

    /**
     * Constructor that takes in the user input split by the " " delimiter into a String[].
     *
     * @param inputTokens User entered line split by a space delimiter.
     */
    public DukeCommandAddTodo(String[] inputTokens) {
        super(inputTokens);
    }

    /**
     * This method will handle adding of a {@link DukeTaskToDo} into {@link DukeTaskList} list of
     *
     * {@link duke.task.DukeTask}.
     * @param tasks Instance of {@link DukeTaskList} which contains an existing list of {@link duke.task.DukeTask}.
     * @param ui Instance of {@link DukeUiMessages} which will show output to the user.
     * @param storage Instance of {@link DukeStorage} which will save the {@link DukeTaskList} to the hard disk.
     */
    @Override
    public void execute(DukeTaskList tasks, DukeUiMessages ui, DukeStorage storage) {
        String todoTaskName = DukeParser.concatStringTokens(inputTokens, 1, (inputTokens.length - 1));
        DukeTaskToDo dukeToDo = new DukeTaskToDo(todoTaskName);
        tasks.addToDukeTasks(dukeToDo, ui, storage);
    }
}
