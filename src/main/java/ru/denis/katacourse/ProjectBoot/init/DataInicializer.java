package ru.denis.katacourse.ProjectBoot.init;

import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.denis.katacourse.ProjectBoot.model.Role;
import ru.denis.katacourse.ProjectBoot.model.User;
import ru.denis.katacourse.ProjectBoot.service.RoleService;
import ru.denis.katacourse.ProjectBoot.service.UserService;

import java.util.HashSet;
import java.util.Set;

public class DataInicializer {
    private final UserService userService;
    private final RoleService roleService;

    public DataInicializer(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    private void dataBase() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        Set<Role> adminSet = new HashSet<>();
        Set<Role> userSet = new HashSet<>();

        roleService.addRole(roleAdmin);
        roleService.addRole(roleUser);
        adminSet.add(roleAdmin);
        userSet.add(roleUser);
        User newUser = new User("Ivan", 25, "vanya@mail.com", "123", userSet);
        User admin = new User("Petya", 30, "petya@mail.com", "123", adminSet);

        userService.saveUser(newUser);
        userService.saveUser(admin);
    }

}