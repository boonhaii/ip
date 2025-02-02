package duke.command;

import java.util.ArrayList;
import java.util.Arrays;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

public class DeleteCommand extends Command {
    private static final boolean IS_EXIT = false;
    private int[] taskIds;
    private Task removedTask;

    /**
     * Public constructor of the DeleteCommand object.
     * @param taskIds The id(s) of the task to be removed, with respect to
     *               the list printed by the list() command.
     */
    public DeleteCommand(int ...taskIds) {
        this.taskIds = taskIds;
    }

    /**
     * Removes the task in the respective component of the bot.
     *
     * @param tasks The TaskList associated with the current bot.
     * @param userInt The User Interface associated with the current bot.
     * @param storage The storage associated with the current bot.
     * @return The string to be printed to the GUI.
     * @throws DukeException If any error has occurred during the addition of the task.
     *
     */
    @Override
    public String execute(TaskList tasks, Ui userInt, Storage storage) throws DukeException {
        if (taskIds.length == 1) {
            removedTask = tasks.delete(this.taskIds[0]);
            storage.save(tasks);
            return userInt.notifyDelete(removedTask);
        } else {
            // To remove items with the biggest ids first, such that it would not affect later deletes.
            Arrays.sort(this.taskIds);
            ArrayList<Task> removedTasks = new ArrayList<>();
            for (int i = this.taskIds.length - 1; i >= 0; i--) {
                removedTask = tasks.delete(this.taskIds[i]);
                removedTasks.add(0, removedTask);
            }
            storage.save(tasks);
            return userInt.notifyMultiDelete(removedTasks);
        }
    }

    /**
     * Gets the task associated with the command.
     * If multiple, gives the most recently deleted task.
     *
     * @return The task associated with the given command.
     */
    @Override
    public Task getTask() {
        return this.removedTask;
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
}
