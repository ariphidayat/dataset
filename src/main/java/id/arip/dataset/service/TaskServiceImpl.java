package id.arip.dataset.service;

import id.arip.dataset.model.Task;
import id.arip.dataset.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> getTasks() {
        return taskRepository.getTasks();
    }

    @Override
    public Task getTask(Integer taskId) {
        return taskRepository.getTask(taskId);
    }

    @Override
    public void save(Task task) {
        taskRepository.save(task);
    }

    @Override
    public void delete(Integer taskId) {
        Task task = getTask(taskId);
        taskRepository.delete(task);
    }

    @Override
    public void book(Task task) {
        taskRepository.book(task);
    }

    @Override
    public void revoke(Task task) {
        taskRepository.revoke(task);
    }
}
