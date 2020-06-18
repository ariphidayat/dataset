package id.arip.dataset.service;

import id.arip.dataset.model.User;
import id.arip.dataset.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    @Override
    public User getUser(String username) {
        return userRepository.getUser(username);
    }

    @Override
    public void save(User user) {
        if (userRepository.getUser(user.getUsername()) == null) {
            userRepository.save(user);
        } else {
            userRepository.update(user);
        }
    }

    @Override
    public void delete(String username) {
        User user = userRepository.getUser(username);
        if (user != null) {
            userRepository.delete(user);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return getUser(s);
    }
}
