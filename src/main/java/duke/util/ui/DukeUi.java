package duke.util.ui;

import duke.Duke;
import duke.command.DukeCommand;
import duke.command.DukeCommandClear;
import duke.util.DukeParser;
import duke.util.DukeStorage;
import duke.util.DukeTaskList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class DukeUi extends Application {

    private Duke duke;
    private DukeUiMessages ui;
    private DukeStorage storage;
    private DukeTaskList tasks;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Reads the input from the input textfield and processes the input command. Clears the user input after processing.
     */
    private void handleUserInput() {
        String input = userInput.getText();
        userInput.clear();
        addAsLabelToDisplay(input);
        Optional<DukeCommand> optionalCommand = DukeParser.parseCommand(input, ui);
        if (!optionalCommand.isEmpty()) {
            DukeCommand command = optionalCommand.get();
            if (command instanceof DukeCommandClear) {
                ((DukeCommandClear) command).execute(this);
            } else {
                command.execute(tasks, ui, storage);
            }
        }
    }

    /**
     * Initializes all the events for the program's GUI.
     */
    private void initEventHandling() {
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     * Obtains the context of the logic components.
     */
    private void initLogicElements() {
        duke = new Duke(Duke.DUKE_TASK_FILE_PATH);
        storage = duke.getStorage();
        tasks = duke.getTasks();
        ui = duke.getUi();
        ui.initUiComponents(this);
    }

    /**
     * Initializes all the UI elements and sets their sizes. A Scene object will be initialized and have every element
     * added to it at the end.
     */
    private void initUIElements() {
        /* The container for the content of the chat to scroll.
           |------AnchorPane------|
           ||-----ScrollPane-----||
           |||-------VBox-------|||
           |||                  |||
           |||------------------|||
           ||--------------------||
           |<---TextField---><Btn>|
           |----------------------|
         */
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        //Dialog area size and responsiveness
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Input textfield
        userInput = new TextField();
        userInput.setPrefWidth(325.0);

        //Send button
        sendButton = new Button("Send");
        sendButton.setPrefWidth(55.0);

        //Add all elements into the main window
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        mainLayout.setPrefSize(385.0, 600.0);
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        scene = new Scene(mainLayout);
    }

    /**
     * Prepares the main application GUI window.
     *
     * @param stage Stage object that will be run with.
     */
    private void prepareStage(Stage stage) {
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
    }

    /**
     * Duke Program entry point. Initializes the main window UI, as well as the underlying logic components.
     * The event handling is also initialized. Finally, the program will print the welcome message and continuously
     * wait for user input.
     *
     * @param stage Stage object that will be run with.
     */
    @Override
    public void start(Stage stage) {
        initUIElements();
        prepareStage(stage);
        initLogicElements();
        initEventHandling();

        //Print the welcome logo
        ui.displayWelcomeMessage();
    }

    /**
     * Adds the specified String into the dialog container as a Label.
     *
     * @param input String to add to the dialog container as a Label.
     */
    public void addAsLabelToDisplay(String input) {
        Label output = new Label(input);
        output.setWrapText(true);
        dialogContainer.getChildren().add(output);
    }

    /**
     * Clears the textArea of all Labels.
     */
    public void clearTextRegion() {
        dialogContainer.getChildren().clear();
    }



}
