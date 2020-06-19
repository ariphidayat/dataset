package id.arip.dataset.repository;

import id.arip.dataset.model.Task;

import java.util.List;

public interface TaskRepository {

    List<Task> getTasks();
    Task getTask(Integer taskId);
    void save(Task task);
    void delete(Task task);
    void book(Task task);
    void revoke(Task task);
}
