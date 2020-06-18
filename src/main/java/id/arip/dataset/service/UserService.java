package id.arip.dataset.service;

import id.arip.dataset.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User getUser(String username);
}
