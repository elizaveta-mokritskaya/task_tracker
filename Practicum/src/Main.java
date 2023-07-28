import ru.yandex.practicum.task_tracker.manager.TaskTracker;
import ru.yandex.practicum.task_tracker.tasks.Epic;
import ru.yandex.practicum.task_tracker.tasks.Subtask;
import ru.yandex.practicum.task_tracker.tasks.Task;

public class Main {
    public static void main(String[] args) {
        TaskTracker taskTracker = new TaskTracker();
        Epic[] epics = new Epic[] {
            new Epic("Ужин", "Готовим праздничный ужин: утку в медовом соусе, салат с креветками"),
            new Epic("abab", "qwerty"),
        };
        long epic1Id = taskTracker.addNewEpic(epics[0]);
        long epic2Id = taskTracker.addNewEpic(epics[1]);
        Subtask[] subtasks = new Subtask[] {
            new Subtask("Утка", "Маринуем и жарим стейки", epic1Id),
            new Subtask("сАлат", "Маринуем и жарим стейки", epic1Id),
            new Subtask("Третий эпик", "Маринуем и жарим стейки", epic2Id)
        };

        long subtask1Id = taskTracker.addNewSubtask(subtasks[0]);
        long subtask2Id = taskTracker.addNewSubtask(subtasks[1]);
        long subtask3Id = taskTracker.addNewSubtask(subtasks[2]);

        for(Subtask subtask: taskTracker.getAllSubtasksByEpicId(epic1Id)) {
            System.out.println(subtask);
        }

        subtasks[0].setStatus(Task.Status.DONE);
        taskTracker.updateSubtask(subtasks[0]);
        for(Subtask subtask: taskTracker.getAllSubtasksByEpicId(epic1Id)) {
            System.out.println(subtask);
        }

    }


}