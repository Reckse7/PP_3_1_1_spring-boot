package springboot.app.service;

import springboot.app.model.User;

import java.util.List;

public interface UserService {
    void save(User user);
    void delete(int id);
    User getById(int id);
    List<User> getAllUsers();
}
