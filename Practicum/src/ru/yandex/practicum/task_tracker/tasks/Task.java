package ru.yandex.practicum.task_tracker.tasks;

import java.util.Objects;

public class Task {

    protected long id;
    protected String name;
    protected String desc;
    protected Status status;

    public Task(String name, String desc, Status status) {
        this.name = name;
        this.desc = desc;
        this.status = status;
    }
//todo equals переопределить в каждом классе
    public void setId(long generateId) {
        this.id = generateId;
    }

    public enum Status {
        NEW,
        IN_PROGRESS,
        DONE
    }

    @Override
    public String toString() {
        return "Task{" +
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
        Task task = (Task) o;
        return id == task.id && name.equals(task.name) && desc.equals(task.desc) && status.equals(task.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, desc, status);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public Status getStatus() {
        return status;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}
