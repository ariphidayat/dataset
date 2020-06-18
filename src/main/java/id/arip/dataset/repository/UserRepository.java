package id.arip.dataset.repository;

import id.arip.dataset.model.User;

import java.util.List;

public interface UserRepository {

    List<User> getUsers();
    User getUser(String username);
    void save(User user);
    void update(User user);
    void delete(User user);
}
