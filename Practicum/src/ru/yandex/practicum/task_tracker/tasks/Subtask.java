package ru.yandex.practicum.task_tracker.tasks;

import java.util.Objects;

public class Subtask extends Task {
    private long epicId;

    public Subtask(String name, String desc,long epicId) {
        super(name, desc, Status.NEW);

        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "Subtask{" +
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
        Subtask subtask = (Subtask) o;
        return epicId == subtask.epicId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), epicId);
    }

    public Long getEpicId() {
        return epicId;
    }

}
