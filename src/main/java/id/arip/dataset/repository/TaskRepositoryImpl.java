package id.arip.dataset.repository;

import id.arip.dataset.model.Task;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Task> getTasks() {
        return sessionFactory.getCurrentSession().createQuery("FROM Task WHERE deleted = 0").list();
    }

    @Override
    public Task getTask(Integer taskId) {
        return sessionFactory.getCurrentSession().get(Task.class, taskId);
    }

    @Override
    public void save(Task task) {
        sessionFactory.getCurrentSession().save(task);
    }

    @Override
    public void delete(Task task) {
        Task taskToUpdate = getTask(task.getTaskId());
        taskToUpdate.setDeleted(1);
        sessionFactory.getCurrentSession().update(taskToUpdate);
    }

    @Override
    public void book(Task task) {
        Task taskToBook = getTask(task.getTaskId());
        taskToBook.setBookedBy(task.getBookedBy());
        sessionFactory.getCurrentSession().update(taskToBook);
    }

    @Override
    public void revoke(Task task) {
        Task taskToBook = getTask(task.getTaskId());
        taskToBook.setBookedBy(null);
        sessionFactory.getCurrentSession().update(taskToBook);
    }
}
