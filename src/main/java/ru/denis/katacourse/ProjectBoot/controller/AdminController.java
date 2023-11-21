package ru.denis.katacourse.ProjectBoot.controller;



import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.denis.katacourse.ProjectBoot.model.User;
import ru.denis.katacourse.ProjectBoot.service.UserService;


import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
    private final UserService userService;


    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String printWelcome(@AuthenticationPrincipal User user, ModelMap model) {
        model.addAttribute("user", user);
        return "hello";
    }


    @GetMapping("/people")
    public String index(Model model) {
        model.addAttribute("people", userService.getAllUsers());
        return "view/index";
    }

    @GetMapping("/people/new")
    public String newUser(@ModelAttribute("user") User user){
        return "view/new";
    }

    @PostMapping("/people")
    public String create(@ModelAttribute("user") @Valid User user,
                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "view/new";
        }
        userService.saveUser(user);
        return "redirect:/people";
    }

    @DeleteMapping("/people/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        userService.removeUserById(id);
        return "redirect:/people";
    }

    @GetMapping("/people/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "view/edit";
    }

    @PatchMapping("/people/{id}")
    public String updatePerson(@ModelAttribute("user") @Valid User updateUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "view/edit";
        }
        userService.updateUser(updateUser);
        return "redirect:/people";
    }
}
