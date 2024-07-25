package org.example.service;

import org.example.model.User;

import java.util.List;

public interface UserService {
    void createUserTable();
    String saveUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    String updateUser(Long userId, User user);
    void deleteUserById(Long id);
}
