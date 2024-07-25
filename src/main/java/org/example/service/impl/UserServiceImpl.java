package org.example.service.impl;

import org.example.dao.UserDao;
import org.example.dao.impl.UserDaoImpl;
import org.example.model.User;
import org.example.service.UserService;

import java.util.List;


public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoImpl();


    @Override
    public void createUserTable() {
        userDao.createUserTable();

    }

    @Override
    public String saveUser(User user) {
        return userDao.saveUser(user);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public String updateUser(Long userId, User user) {
        return userDao.updateUser(userId, user);
    }

    @Override
    public void deleteUserById(Long id) {
        userDao.deleteUserById(id);

    }
}
