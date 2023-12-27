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




    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;

    }

    @GetMapping()
    public String printWelcome(@AuthenticationPrincipal User user, ModelMap model) {
        model.addAttribute("user", user);

        return "admin/adminPage";
    }


    @GetMapping("/people")
    public String index(@AuthenticationPrincipal User user,Model model) {
        model.addAttribute("user", user);
        model.addAttribute("people", userService.getAllUsers());
        model.addAttribute("roles", roleService.allRoles());
        return "admin/index";
    }

    @GetMapping("/people/new")
    public String newUser(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("people", userService.getAllUsers());
        model.addAttribute("roles", roleService.allRoles());
        return "admin/new";
    }

    @PostMapping("/people")
    public String create(@ModelAttribute("user") @Valid User user,
                         @RequestParam(value = "checkedRoles") String[] selectResult,
                         BindingResult bindingResult)  {

        for (String s : selectResult) {
            user.addRole(roleService.getRole("ROLE_" + s));
        }
        if (bindingResult.hasErrors()) {
            return "admin/new";
        }
        userService.passEncod(user);
        userService.saveUser(user);
        return "redirect:/admin/people";
    }

    @DeleteMapping("/people/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        userService.removeUserById(id);
        return "redirect:/admin/people";
    }


    @PostMapping("/people/update/{id}")
    public String updatePerson(@ModelAttribute("user") @Valid User updateUser,
                               @RequestParam(value = "userRolesSelector") String[] selectResult,
                               @PathVariable("id") int id) {



        for (String s : selectResult) {
            updateUser.addRole(roleService.getRole("ROLE_" + s));
        }

        User user = userService.getUserById(id);

        if (!(user.getPassword()).equals(updateUser.getPassword())) {
            userService.passEncod(updateUser);
        }
        userService.updateUser(updateUser);
        return "redirect:/admin/people";


    }

}
