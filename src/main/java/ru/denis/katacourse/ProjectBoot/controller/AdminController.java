package ru.denis.katacourse.ProjectBoot.controller;



import jakarta.validation.Valid;
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
    private UserService dao;


    public AdminController(UserService dao) {
        this.dao = dao;
    }

    @GetMapping(value = "/")
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("I'm Spring MVC application");
        messages.add("5.2.0 version by sep'19 ");
        model.addAttribute("messages", messages);
        return "hello";
    }


    @GetMapping("/people")
    public String index(Model model) {
        model.addAttribute("people", dao.getAllUsers());
        return "view/index";
    }

    @GetMapping("/people/new")
    public String newUser(@ModelAttribute("user") User user){
        return "view/new";
    }

    @PostMapping("/people")
    public String creat(@ModelAttribute("user") @Valid User user,
                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "view/new";
        }
        dao.saveUser(user);
        return "redirect:/people";
    }

    @DeleteMapping("/people/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        dao.removeUserById(id);
        return "redirect:/people";
    }

    @GetMapping("/people/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", dao.getUserById(id));
        return "view/edit";
    }

    @PatchMapping("/people/{id}")
    public String updatePerson(@ModelAttribute("user") @Valid User updateuser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "view/edit";
        }
        dao.updateUser(updateuser);
        return "redirect:/people";
    }
}
