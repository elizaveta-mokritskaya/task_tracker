package ru.yandex.practicum.task_tracker.manager;
import ru.yandex.practicum.task_tracker.tasks.Epic;
import ru.yandex.practicum.task_tracker.tasks.Subtask;
import ru.yandex.practicum.task_tracker.tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskTracker {
    private HashMap<Long, Task> tasks = new HashMap<>();
    private HashMap<Long, Epic> epics = new HashMap<>();
    private HashMap<Long, Subtask> subtasks = new HashMap<>();
    private long generatorId = 0;

//    public long addNewTask(Task task) {
//        return addNewTask(task.getName(), task.getDesc(), task.getStatus());
//    }
//
//    public long addNewTask(String name, String desc, Task.Status status){
//        Task task = new Task(generateId(), name, desc, status);
//        tasks.put(task.getId(), task);
//        return task.getId();
//    }
//
//    public long addNewEpic(String name, String desc) {
//        Epic epic = new Epic(generateId(), name, desc);
//        epics.put(epic.getId(), epic);
//        return epic.getId();
//    }

//    public long addNewSubtask(String name, String desc, long epicId) {
//        Epic epic = epics.get(epicId);
//        if (epic == null) {
//            System.out.println("Нет Epic с id=" + epicId);
//            return -1;
//        }
//        Subtask subtask = new Subtask(generateId(), name, desc, epicId);
//        subtasks.put(subtask.getId(), subtask);
//        epic.addSubtaskId(subtask.getId());
//        updateEpicStatus(subtask.getEpicId());
//
//        return subtask.getId();
//    }
    public long addNewTask(Task task) {
        task.setId(generateId());
        tasks.put(task.getId(), task);
        return task.getId();
    }

    public long addNewEpic(Epic epic) {
        epic.setId(generateId());
        epics.put(epic.getId(), epic);
        return epic.getId();
    }

    public long addNewSubtask(Subtask subtask) {
        Epic epic = epics.get(subtask.getEpicId());
        if (epic == null) {
            System.out.println("Нет Epic с id=" + subtask.getEpicId());
            return -1;
        }
        subtask.setId(generateId());
        subtasks.put(subtask.getId(), subtask);
        epic.addSubtaskId(subtask.getId());
        updateEpicStatus(subtask.getEpicId());

        return subtask.getId();
    }
    public void updateTask(Task task){
        Task savedTask = tasks.get(task.getId());
        if (savedTask == null) {
            System.out.println("Нет Task с id=" + task.getId());
            return;
        }
        tasks.put(task.getId(), task);
    }
    public void updateSubtask(Subtask subtask) {
        Subtask savedSubtask = subtasks.get(subtask.getId());
        if (savedSubtask == null) {
            System.out.println("Нет Subtask с id=" + subtask.getId());
            return;
        }
        subtasks.put(subtask.getId(), subtask);
        updateEpicStatus(subtask.getEpicId());
    }

    public void updateEpic(Epic epic) {
        Epic savedepic = epics.get(epic.getId());
        if (savedepic == null) {
            System.out.println("Нет Epic с id=" + epic.getId());
            return;
        }
       epics.put(epic.getId(), epic);

        //TODO получить Эпик из хранилища, проверка на ноль, обновиь название и описание,
    //todo будет ли этот метод что-то возвращать? !!!!Да, будет возвращать сам объект а не айдишник
        //todo какой у него параметр: Epic или long epicId?
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public List<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    public List<Subtask> getAllSubtasksByEpicId(long epicId) {
        Epic epic = epics.get(epicId);
        List<Subtask> subtaskList = new ArrayList<>();
        for(long id : epic.getSubtaskIds()) {
            subtaskList.add(subtasks.get(id));
        }
        return subtaskList;

    }

    public Task getTaskById(long taskId) {
        return tasks.get(taskId);
    }

    public Epic getEpicById(long epicId) {
        return epics.get(epicId);
    }

    public Subtask getSubtaskById(long subtaskId) {
        return subtasks.get(subtaskId);
    }

    public Subtask removeSubtaskById(long subtaskId) {
        return subtasks.remove(subtaskId);
    }

    public void clearSubtasksByEpicId(long epicId) {
        Epic epic = epics.get(epicId);
        if (epic == null) {
            return;
        }
        for (long subtaskId : epic.getSubtaskIds()) {
            removeSubtaskById(subtaskId);
        }
    }

    public void clearTasks() {
        tasks.clear();
    }

    public void clearEpics() {
        epics.clear();
    }



    private void updateEpicStatus(long epicId) {
        Epic epic = epics.get(epicId);
        ArrayList<Long> subtaskIds = epic.getSubtaskIds();
        if (subtaskIds.isEmpty()) {
            epic.setStatus(Task.Status.NEW);
            return;
        }
        //todo Какой вариант лучше?
//        if (subtaskIds.stream().allMatch(subtaskId -> {
//            Subtask subtask = subtasks.get(subtaskId);
//            return subtask.getStatus().equals(Task.Status.NEW);
//        })) {
//            epic.setStatus(Task.Status.NEW);
//            return;
//        }
//        if (subtaskIds.stream().allMatch(subtaskId -> {
//            Subtask subtask = subtasks.get(subtaskId);
//            return subtask.getStatus().equals(Task.Status.DONE);
//        })) {
//            epic.setStatus(Task.Status.DONE);
//            return;
//        }
//        epic.setStatus(Task.Status.IN_PROGRESS);
        Task.Status status = null;
        for(long subtaskId : subtaskIds) {
            Subtask subtask = subtasks.get(subtaskId);

            if(status == null) {
                status = subtask.getStatus();
                continue;
            }
            if (status.equals(subtask.getStatus())
                    && !status.equals(Task.Status.IN_PROGRESS)) {
                continue;
            }

            epic.setStatus(Task.Status.IN_PROGRESS);
            return;
        }

        epic.setStatus(status);
    }

    private long generateId() {
        return generatorId++;
    }
}
