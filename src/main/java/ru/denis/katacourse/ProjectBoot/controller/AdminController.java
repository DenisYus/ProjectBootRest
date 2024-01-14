package ru.denis.katacourse.ProjectBoot.controller;



import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.denis.katacourse.ProjectBoot.model.Role;
import ru.denis.katacourse.ProjectBoot.model.User;
import ru.denis.katacourse.ProjectBoot.service.RoleService;
import ru.denis.katacourse.ProjectBoot.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;




    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;

    }
    @GetMapping("/allUsers")
    public ResponseEntity<Iterable<User>> getAllUsers() {
        final List<User> userDtoList = userService.getAllUsers();

        return userDtoList != null && !userDtoList.isEmpty()
                ? new ResponseEntity<>(userDtoList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAllRoles")
    public ResponseEntity<Iterable<Role>> getAllRoles() {
        List<Role> roleList = roleService.allRoles();
        return roleList != null && !roleList.isEmpty()
                ? new ResponseEntity<>(roleList.stream().toList(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/userById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        final User userDto = userService.getUserById(id);
        return userDto != null
                ? new ResponseEntity<>(userDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("id") int id) {
        userService.updateUser(user, id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {
        userService.removeUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    

}
