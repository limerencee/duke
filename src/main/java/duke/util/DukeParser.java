package duke.util;

import duke.command.DukeCommand;
import duke.command.DukeCommandAdd;
import duke.command.DukeCommandClear;
import duke.command.DukeCommandExit;
import duke.command.DukeCommandList;
import duke.command.DukeCommandUpdate;
import duke.util.ui.DukeUiMessages;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DukeParser {

    private static final String DUKE_DATETIME_INPUT_FORMAT = "d/M/yyyy HHmm";
    private static final String DUKE_DATETIME_OUTPUT_FORMAT = "MMMM uuuu, h:mma";

    private enum DukeCommandEnum {
        BYE, CLEAR, DEADLINE, DELETE, DONE, EVENT, FIND, LIST, TODO
    }

    /**
     * Concatenates the input String[] from the specified starting index, with a space delimiter between each token
     * with the exception of the last token. Returns the concatenated String.
     *
     * @param inputTokens Input String array to concatenate from.
     * @param startIndex Starting index (inclusive) to start concatenating from.
     * @param endIndex Ending index (inclusive) to end concatenating at.
     * @return A concatenated String starting from the startIndex to the endIndex, with a single-space delimiter except
     *     after the last token.
     */
    public static String concatStringTokens(String[] inputTokens, int startIndex, int endIndex) {
        assert startIndex >= 0 && startIndex < inputTokens.length;
        assert endIndex > 0 && endIndex < inputTokens.length;
        assert startIndex < endIndex;
        StringBuilder sb = new StringBuilder();
        while (startIndex < endIndex) {
            sb.append(inputTokens[startIndex++] + " ");
        }
        sb.append(inputTokens[startIndex]);
        return sb.toString();
    }

    /**
     * Takes a input String date-time in the format {@link #DUKE_DATETIME_INPUT_FORMAT} and attempts to create a
     * LocalDateTime object. Then, depending on the day of the month, there will be a suffix at the end. For example,
     * 1st, 2nd, 3rd, 4th, etc. This LocalDateTime object is then formatted to the format
     * {@link #DUKE_DATETIME_OUTPUT_FORMAT}
     *
     * @param input Date-time String in the format "d/MM/uuuu HHmm". E.g. "2/12/2019 1800".
     * @return Date-time String in the format: "ddth of MM uuuu, h:mma". E.g. "2nd of December 2019, 6:00PM".
     * @throws DateTimeParseException If the input String does not match the required format.
     */
    public static String formatDate(String input) throws DateTimeParseException {
        assert !input.equals("");
        Map<Long, String> ordinalNumbers = new HashMap<>(31);
        ordinalNumbers.put(1L, "1st");
        ordinalNumbers.put(2L, "2nd");
        ordinalNumbers.put(3L, "3rd");
        ordinalNumbers.put(21L, "21st");
        ordinalNumbers.put(22L, "22nd");
        ordinalNumbers.put(23L, "23rd");
        ordinalNumbers.put(31L, "31st");
        for (long d = 1; d <= 31; d++) {
            ordinalNumbers.putIfAbsent(d, "" + d + "th");
        }

        DateTimeFormatter dayOfMonthFormatter = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH, ordinalNumbers)
                .appendLiteral(" of ")
                .appendPattern(DUKE_DATETIME_OUTPUT_FORMAT)
                .toFormatter();

        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern(DUKE_DATETIME_INPUT_FORMAT);
        LocalDateTime inputDateTime = LocalDateTime.parse(input, dateTimeFormat);
        return inputDateTime.format(dayOfMonthFormatter);
    }

    /**
     * Gets the starting index of the parameter of the supplied flag in the input String array.
     *
     * @param inputTokens Array of String to check for if the flag exists.
     * @param flag Exact String to look for.
     * @return Starting index of the flag's parameter, -1 if flag is not found in the array.
     */
    public static int getInputFlagParameterStartingIndex(String[] inputTokens, String flag) {
        for (int counter = 0; counter < inputTokens.length; counter++) {
            if (inputTokens[counter].equals(flag)) {
                return counter + 1;
            }
        }
        return -1;
    }

    /**
     * Checks the user input to determine the course of action depending on the command. If the command is to "TODO/
     * DEADLINE/EVENT", a {@link DukeCommandAdd} class will be instantiated and returned. If the command is to "DONE/
     * DELETE", a {@link DukeCommandUpdate} class will be instantiated and returned. If the command is to "LIST/FIND", a
     * {@link DukeCommandList} class will be instantiated and returned. If the command is to "BYE", a
     * {@link DukeCommandExit} class will be instantiated and returned. An Optional.empty() will be returned if the
     *     user input cannot be parsed.
     *
     * @param input Raw user input String obtained from the input TextField.
     * @param ui Instance of {@link DukeUiMessages} which will show output to the user.
     * @return Optional&lt;DukeCommand&gt; which is empty if the user input cannot be parsed, or a {@link DukeCommand}
     *     sub-class which has a {@link DukeCommand#execute(DukeTaskList, DukeUiMessages, DukeStorage)} method.
     */
    public static Optional<DukeCommand> parseCommand(String input, DukeUiMessages ui) {
        String[] inputTokens = input.split(" ");
        try {
            DukeCommandEnum inputCommand = DukeCommandEnum.valueOf(inputTokens[0].toUpperCase());
            switch (inputCommand) {
            case BYE:
                return Optional.of(new DukeCommandExit());

            case CLEAR:
                return Optional.of(new DukeCommandClear());

            case DEADLINE:
            case EVENT:
            case TODO:
                return Optional.of(new DukeCommandAdd(inputTokens));

            case DELETE:
            case DONE:
                return Optional.of(new DukeCommandUpdate(inputTokens));

            case FIND:
            case LIST:
                return Optional.of(new DukeCommandList(inputTokens));

            default:
                return Optional.empty();
            }
        } catch (IllegalArgumentException ex) {
            ui.displayUnknownCommand();
            return Optional.empty();
        }
    }
}
