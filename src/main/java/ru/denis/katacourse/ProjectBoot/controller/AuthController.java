package ru.denis.katacourse.ProjectBoot.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.denis.katacourse.ProjectBoot.model.User;
import ru.denis.katacourse.ProjectBoot.service.RegistrationUser;
import ru.denis.katacourse.ProjectBoot.service.RoleService;

import java.util.stream.Collectors;


@Controller
@RequestMapping("/auth")
public class AuthController {
    private final RegistrationUser registrationUser;
    private final RoleService roleService;





    public AuthController(RegistrationUser registrationUser, RoleService roleService) {
        this.registrationUser = registrationUser;

        this.roleService = roleService;
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
    public String performRegistration(@ModelAttribute("user") @Valid User user,
                                      @RequestParam(value = "checkedRegistration") String[] selectResult,
                                      BindingResult bindingResult){
        for (String s : selectResult) {
            user.addRole(roleService.getRole(s));
        }
        if (bindingResult.hasErrors()) {
            return "/auth/registration"; }

        registrationUser.register(user);
        return "redirect:/auth/login";
    }

}
