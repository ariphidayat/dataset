package id.arip.dataset.service;

import id.arip.dataset.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getUsers();
    User getUser(String username);
    void save(User user);
    void delete(String username);
}
