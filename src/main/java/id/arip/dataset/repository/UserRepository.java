package id.arip.dataset.repository;

import id.arip.dataset.model.User;

public interface UserRepository {

    User getUser(String username);
}
