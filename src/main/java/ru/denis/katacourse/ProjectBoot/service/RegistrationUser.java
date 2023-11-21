package ru.denis.katacourse.ProjectBoot.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.denis.katacourse.ProjectBoot.dao.UserDAO;
import ru.denis.katacourse.ProjectBoot.model.User;
@Service
public class RegistrationUser {
    private final UserDAO userDAO;

    public RegistrationUser(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    @Transactional
    public void register(User user){
        userDAO.saveUser(user);
    }
}
