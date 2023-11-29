package ru.denis.katacourse.ProjectBoot.controller;



import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.denis.katacourse.ProjectBoot.model.User;
import ru.denis.katacourse.ProjectBoot.service.RoleService;
import ru.denis.katacourse.ProjectBoot.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;


    public AdminController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping()
    public String printWelcome(@AuthenticationPrincipal User user, ModelMap model) {
        model.addAttribute("user", user);
        return "admin/adminPage";
    }


    @GetMapping("/people")
    public String index(Model model) {
        model.addAttribute("people", userService.getAllUsers());
        model.addAttribute("roles", roleService.allRoles());
        return "admin/index";
    }

    @GetMapping("/people/new")
    public String newUser(@ModelAttribute("user") User user){
        return "admin/new";
    }

    @PostMapping("/people")
    public String create(@ModelAttribute("user") @Valid User user,
                         @RequestParam(value = "checkedRoles") String[] selectResult,
                        BindingResult bindingResult) {
        for (String s : selectResult) {
            user.addRole(roleService.getRole(s));
        }
        if (bindingResult.hasErrors()) {
            return "admin/new";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        return "redirect:/admin/people";
    }

    @DeleteMapping("/people/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        userService.removeUserById(id);
        return "redirect:/admin/people";
    }

    @GetMapping("/people/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "admin/edit";
    }

    @PatchMapping("/people/{id}")
    public String updatePerson(@ModelAttribute("user") @Valid User updateUser,
                               @RequestParam(value = "checkedRoles") String[] selectResult,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/edit";
        }
        userService.updateUser(updateUser);
        return "redirect:/admin/people";
    }

}
