package id.arip.dataset.repository;

import id.arip.dataset.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<User> getUsers() {
        return sessionFactory.getCurrentSession().createQuery("FROM User").list();
    }

    @Override
    public User getUser(String username) {
        return sessionFactory.getCurrentSession().get(User.class, username);
    }

    @Override
    public void save(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void update(User user) {
        User userToUpdate = getUser(user.getUsername());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setRole(user.getRole());
        sessionFactory.getCurrentSession().update(userToUpdate);
    }

    @Override
    public void delete(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }
}
