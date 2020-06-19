package id.arip.dataset.service;

import id.arip.dataset.model.Task;

import java.util.List;

public interface TaskService {

    List<Task> getTasks();
    Task getTask(Integer taskId);
    void save(Task task);
    void delete(Integer taskId);
    void book(Task task);
    void revoke(Task task);
}
