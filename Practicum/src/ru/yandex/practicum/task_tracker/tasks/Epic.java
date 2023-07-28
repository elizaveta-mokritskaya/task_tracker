package ru.yandex.practicum.task_tracker.tasks;

import java.util.ArrayList;
import java.util.Objects;

public class Epic extends Task {
    private ArrayList<Long> subtaskIds;

    public Epic(String name, String desc) {
        super(name, desc, Status.NEW);
        subtaskIds = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Epic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Epic epic = (Epic) o;
        return subtaskIds.equals(epic.subtaskIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subtaskIds);
    }

    public ArrayList<Long> getSubtaskIds() {
        return subtaskIds;
    }

    public void addSubtaskId(long subtaskId) {
        subtaskIds.add(subtaskId);
    }
}
