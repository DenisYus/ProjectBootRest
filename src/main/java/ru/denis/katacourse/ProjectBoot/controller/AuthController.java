package ru.denis.katacourse.ProjectBoot.controller;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.denis.katacourse.ProjectBoot.model.User;
import ru.denis.katacourse.ProjectBoot.service.RoleService;
import ru.denis.katacourse.ProjectBoot.service.UserService;

import java.security.Principal;


@RestController
@RequestMapping("/auth")
public class AuthController {
    private final RoleService roleService;
    private final UserService userService;

    public AuthController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/login")
    public ResponseEntity<?> getAuth(Principal principal) {
    userService.loadUserByUsername(principal.getName());
    return new ResponseEntity<>(HttpStatus.OK);
    }



}
