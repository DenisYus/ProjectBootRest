package ru.denis.katacourse.ProjectBoot.service;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;
import ru.denis.katacourse.ProjectBoot.dao.UserDAO;
import ru.denis.katacourse.ProjectBoot.model.User;


import java.util.List;

@Service

public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;


    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    @Override
    @Transactional
    public void updateUser(User updateUser) {
        userDAO.updateUser(updateUser);
    }

    @Override
    @Transactional
    public void removeUserById(int id) {
        userDAO.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }
}
