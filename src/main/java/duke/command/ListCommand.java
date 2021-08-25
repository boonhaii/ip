package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.task.Task;
import java.util.ArrayList;

public class ListCommand extends Command {
    private boolean isExit = false;

    @Override
    public void execute(TaskList tasks, UI userInt, Storage storage) {
        ArrayList<Task> taskArrList = tasks.getAllTasks();
        userInt.notifyList(taskArrList);
    }

    @Override
    public Task getTask() {
        return null;
    }

    public boolean isExit() {
        return this.isExit;
    }
}
