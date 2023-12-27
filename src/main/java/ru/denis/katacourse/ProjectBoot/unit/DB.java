package ru.denis.katacourse.ProjectBoot.unit;

import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.denis.katacourse.ProjectBoot.model.Role;
import ru.denis.katacourse.ProjectBoot.model.User;
import ru.denis.katacourse.ProjectBoot.service.RoleService;
import ru.denis.katacourse.ProjectBoot.service.UserService;

import java.util.HashSet;
import java.util.Set;
@Component
public class DB {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;


    public DB(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

@PostConstruct
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
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        userService.saveUser(newUser);
        userService.saveUser(admin);
    }

}