package ru.denis.katacourse.ProjectBoot.service;

import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.denis.katacourse.ProjectBoot.dao.UserDAO;
import ru.denis.katacourse.ProjectBoot.model.User;
@Service
public class RegistrationUser {
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;
    public RegistrationUser(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }
    @Transactional
    public void register(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.saveUser(user);
    }
}
