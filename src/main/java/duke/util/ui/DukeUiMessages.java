package duke.util.ui;

import java.util.Scanner;

public class DukeUiMessages {

    public static final String DUKE_ASCII_LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String SEPARATOR = "_________________________________________________________________";
    private static final String DUKE_WELCOME_MESSAGE = "Hello! I'm Duke\n\t What can I do for you?";
    private static final String DUKE_EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String DUKE_ERR_EMPTY_DESCRIPTION_MESSAGE = "☹ OOPS!!! The description of a task "
            + "cannot be empty.";
    private static final String DUKE_ERR_EMPTY_SEARCH_TERM = "☹ OOPS!!! The search term is missing.";
    private static final String DUKE_ERR_FILE_IO_EXCEPTION = "☹ OOPS!!! Failed to open file! Is the path correct?";
    private static final String DUKE_ERR_INDEX_OUT_OF_BOUNDS = "☹ OOPS!!! Please enter a valid task index value.";
    private static final String DUKE_ERR_INVALID_DATE_FORMAT = "☹ OOPS!!! Please input the deadline in the following "
            + "format: \"dd/mm/yyyy hhmm\".";
    private static final String DUKE_ERR_INVALID_INDEX = "☹ OOPS!!! Please only enter numeric values for the task "
            + "index.";
    private static final String DUKE_ERR_MISSING_DEADLINE_PARAM = "☹ OOPS!!! The deadline for the task must be "
            + "specified with \"/by\".";
    private static final String DUKE_ERR_MISSING_EVENT_PARAM = "\"☹ OOPS!!! The event parameter must be specified "
            + "with \\\"/at\\\".\"";
    private static final String DUKE_ERR_MISSING_INDEX = "☹ OOPS!!! The index of the completed task is missing.";
    private static final String DUKE_ERR_UNKNOWN_COMMAND_MESSAGE = "☹ OOPS!!! I'm sorry, but I don't know what "
            + "that means :-(";
    private static final String DUKE_ERR_UNKNOWN_TASK = "An error occurred when trying to re-create a task from the "
            + "saved file!";

    private StringBuilder sb;
    private Scanner scanner;
    private DukeUi ui;

    public DukeUiMessages() {
        sb = new StringBuilder();
        scanner = new Scanner(System.in);
        ui = new DukeUi();
    }

    /**
     * Initializes the ui context to the main GUI object.
     *
     * @param ui GUI object for setting the context.
     */
    public void initUiComponents(DukeUi ui) {
        this.ui = ui;
    }

    /**
     * Takes an input String and wrap it with "______" separator before returning the new updated String.
     *
     * @param input String to wrap the separators around.
     * @return String that is wrapped around the separators.
     */
    private String encapsulateOutputWithSeparator(String input) {
        sb.setLength(0);
        sb.append("\t" + SEPARATOR + "\n");
        sb.append("\t " + input + "\n");
        sb.append("\t" + SEPARATOR + "\n");
        return sb.toString();
    }

    /**
     * Displays the Duke ASCII logo and the welcome message to the user through {@link #displayToUser(String)}.
     */
    public void displayWelcomeMessage() {
        displayToUserUnformatted(DUKE_ASCII_LOGO);
        displayToUser(DUKE_WELCOME_MESSAGE);
    }

    /**
     * Displays the goodbye message to the user through {@link #displayToUser(String)}.
     */
    public void displayTerminateMessage() {
        displayToUser(DUKE_EXIT_MESSAGE);
    }

    /**
     * Prints supplied input wrapped with "______" separator.
     * The input is first formatted through {@link #encapsulateOutputWithSeparator(String)}.
     *
     * @param input String to be displayed to the user.
     */
    public void displayToUser(String input) {
        assert !input.equals("");
        ui.addAsLabelToDisplay(encapsulateOutputWithSeparator(input));
    }

    /**
     * Prints supplied input in the supplied raw format
     *
     * @param input String to be displayed to the user without the separator formatting.
     */
    public void displayToUserUnformatted(String input) {
        assert !input.equals("");
        ui.addAsLabelToDisplay(input);
    }

    /**
     * Prints the error message for when the task description is missing from a new task.
     */
    public void displayEmptyDescriptionError() {
        displayToUser(DUKE_ERR_EMPTY_DESCRIPTION_MESSAGE);
    }

    /**
     * Prints the error message for when the search term is empty from using the "find" command.
     */
    public void displayEmptySearchTermError() {
        displayToUser(DUKE_ERR_EMPTY_SEARCH_TERM);
    }

    /**
     * Prints the error message for when the data file failed to load.
     */
    public void displayFileLoadingError() {
        displayToUser(DUKE_ERR_FILE_IO_EXCEPTION);
    }

    /**
     * Prints the error message for when the user entered a date-time format that is different from the syntax.
     */
    public void displayInvalidDateFormat() {
        displayToUser(DUKE_ERR_INVALID_DATE_FORMAT);
    }

    /**
     * Prints the error message for when the deadline parameter is missing from a new deadline.
     */
    public void displayMissingDeadlineParam() {
        displayToUser(DUKE_ERR_MISSING_DEADLINE_PARAM);
    }

    /**
     * Prints the error message for when the event parameter is missing from a new event.
     */
    public void displayMissingEventParam() {
        displayToUser(DUKE_ERR_MISSING_EVENT_PARAM);
    }

    /**
     * Prints the error message for when the index parameter is missing from deleting a task or marking a task as done.
     */
    public void displayMissingIndex() {
        displayToUser(DUKE_ERR_MISSING_INDEX);
    }

    /**
     * Prints the error message for when the index parameter specified for deleting a task or marking a task as done is
     * out of bounds.
     */
    public void displayTaskIndexOutOfBounds() {
        displayToUser(DUKE_ERR_INDEX_OUT_OF_BOUNDS);
    }

    /**
     * Prints the error message for when the index parameter specified for deleting a task or marking a task as done is
     * non-numerical.
     */
    public void displayTaskInvalidIndex() {
        displayToUser(DUKE_ERR_INVALID_INDEX);
    }

    /**
     * Prints the error message for when an unknown user-input is entered.
     */
    public void displayUnknownCommand() {
        displayToUser(DUKE_ERR_UNKNOWN_COMMAND_MESSAGE);
    }

    /**
     * Prints the error for when data read from the data file cannot be understood, thus a Task cannot be restored.
     * This is usually because the data file is corrupted or in an incorrect format.
     */
    public void displayUnknownTask() {
        displayToUser(DUKE_ERR_UNKNOWN_TASK);
    }
}
