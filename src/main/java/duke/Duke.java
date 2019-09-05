package duke;

import duke.util.DukeStorage;
import duke.util.DukeTaskList;
import duke.util.ui.DukeUi;
import duke.util.ui.DukeUiMessages;
import javafx.application.Application;

import java.io.IOException;

public class Duke {

    public static final String DUKE_TASK_FILE_PATH = ".\\data\\duke.txt";

    private DukeStorage storage;
    private DukeTaskList tasks;
    private DukeUiMessages ui;

    public DukeStorage getStorage() {
        return this.storage;
    }

    public DukeTaskList getTasks() {
        return this.tasks;
    }

    public DukeUiMessages getUi() {
        return this.ui;
    }

    /**
     * Constructor takes in a file path String which specifies the location of the data file to save to/load from.
     *
     * @param filePath Relative/Absolute file path where the data file is stored on the hard disk.
     */
    public Duke(String filePath) {
        ui = new DukeUiMessages();
        try {
            storage = new DukeStorage(filePath);
            tasks = new DukeTaskList(storage.load(ui));
        } catch (NullPointerException | IOException ex) {
            ui.displayFileLoadingError();
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        Application.launch(DukeUi.class);
    }
}
