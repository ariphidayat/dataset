package id.arip.dataset.repository;

import id.arip.dataset.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User getUser(String username) {
        return getCurrentSession().get(User.class, username);
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
