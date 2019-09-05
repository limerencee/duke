package duke.command.add;

import duke.command.DukeCommandAdd;
import duke.task.DukeTaskDeadline;
import duke.util.DukeParser;
import duke.util.DukeStorage;
import duke.util.DukeTaskList;
import duke.util.ui.DukeUiMessages;

import java.time.format.DateTimeParseException;

public class DukeCommandAddDeadline extends DukeCommandAdd {

    /**
     * Constructor that takes in the user input split by the " " delimiter into a String[].
     *
     * @param inputTokens User entered line split by a space delimiter.
     */
    public DukeCommandAddDeadline(String[] inputTokens) {
        super(inputTokens);
    }

    /**
     * This method will handle adding of a {@link DukeTaskDeadline} into {@link DukeTaskList} list of
     * {@link duke.task.DukeTask}.
     *
     * @param tasks Instance of {@link DukeTaskList} which contains an existing list of {@link duke.task.DukeTask}.
     * @param ui Instance of {@link DukeUiMessages} which will show output to the user.
     * @param storage Instance of {@link DukeStorage} which will save the {@link DukeTaskList} to the hard disk.
     */
    @Override
    public void execute(DukeTaskList tasks, DukeUiMessages ui, DukeStorage storage) {
        int deadlineParameterIndex = DukeParser.getInputFlagParameterStartingIndex(inputTokens, "/by");
        if (deadlineParameterIndex == -1 || deadlineParameterIndex == inputTokens.length) {
            ui.displayMissingDeadlineParam();
        } else {
            assert deadlineParameterIndex > 2; //minimally: 'deadline name /by (argv)'
            String deadlineTaskName = DukeParser.concatStringTokens(inputTokens, 1,
                    (deadlineParameterIndex - 2));
            String deadlineParameterString = DukeParser.concatStringTokens(inputTokens, deadlineParameterIndex,
                    (inputTokens.length - 1));
            try {
                DukeTaskDeadline dukeDeadline = new DukeTaskDeadline(deadlineTaskName,
                        DukeParser.formatDate(deadlineParameterString));
                tasks.addToDukeTasks(dukeDeadline, ui, storage);
            } catch (DateTimeParseException ex) {
                ui.displayInvalidDateFormat();
            }
        }
    }
}
