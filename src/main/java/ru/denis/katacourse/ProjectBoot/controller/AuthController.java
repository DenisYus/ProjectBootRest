package ru.denis.katacourse.ProjectBoot.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.denis.katacourse.ProjectBoot.model.User;
import ru.denis.katacourse.ProjectBoot.service.RegistrationUser;
import ru.denis.katacourse.ProjectBoot.service.RoleService;
import ru.denis.katacourse.ProjectBoot.service.UserService;


@Controller
@RequestMapping("/auth")
public class AuthController {
    private final RegistrationUser registrationUser;




    public AuthController(RegistrationUser registrationUser, UserService userService, RoleService roleService) {
        this.registrationUser = registrationUser;

    }

    @GetMapping("/login")
    public String loginPage(){
    return "auth/login";
    }
    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user") User user){
        return "auth/registration";
    }
    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("user") @Valid User user, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return "/auth/registration"; }

        registrationUser.register(user);
        return "redirect:/auth/login";
    }
}
