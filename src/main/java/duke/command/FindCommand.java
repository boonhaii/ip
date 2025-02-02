package duke.command;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * FindCommand class which searches for tasks with matching keywords in the description or tags.
 */
public class FindCommand extends Command {
    private static final boolean IS_EXIT = false;
    private String keyword;

    /**
     * Public constructor of the FindCommand
     *
     * @param keyword The keyword used to search for the matching tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Lists the tasks with the matching keyword associated with the FindCommand.
     * @param tasks The TaskList associated with the current bot.
     * @param userInt The User Interface associated with the current bot.
     * @param storage The storage associated with the current bot.
     * @return The string to be printed to the GUI.
     */
    @Override
    public String execute(TaskList tasks, Ui userInt, Storage storage) {
        ArrayList<Task> taskArrList = tasks.getAllTasks();
        int listLength = taskArrList.size();

        ArrayList<Task> matches = new ArrayList<>();
        for (int i = 0; i < listLength; i++) {
            Task currTask = taskArrList.get(i);
            String description = currTask.getTaskName();
            if (description.contains(this.keyword) || currTask.hasGivenTag(this.keyword)) {
                matches.add(currTask);
            }
        }

        if (matches.size() == 0) {
            return userInt.notifyNoMatching();
        } else {
            return userInt.notifyMatchingList(matches);
        }
    }

    /**
     * Returns a boolean determining whether the input should exit the bot.
     *
     * @return If the command exits the bot.
     */
    @Override
    public boolean isExit() {
        return this.IS_EXIT;
    }

    /**
     * Gets the task associated with the command.
     *
     * @return null, as no task is associated with the command.
     */
    @Override
    public Task getTask() {
        return null;
    }
}
