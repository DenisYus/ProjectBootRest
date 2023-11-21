package ru.denis.katacourse.ProjectBoot.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.denis.katacourse.ProjectBoot.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    void saveUser(User user);

    void updateUser(User updateUser);

    void removeUserById(int id);

    List<User> getAllUsers();

    User getUserById(int id);

    UserDetails loadUserByUsername(String email);
}
