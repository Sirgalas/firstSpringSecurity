package ru.sergalas.FirstSecurity.controllers.admin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sergalas.FirstSecurity.controllers.admin.BaseAdminController;
import ru.sergalas.FirstSecurity.entities.users.Roles;
import ru.sergalas.FirstSecurity.entities.users.User;
import ru.sergalas.FirstSecurity.services.admin.AdminServices;
import ru.sergalas.FirstSecurity.services.admin.UserService;

import javax.validation.Valid;

@Controller
public class UserController extends BaseAdminController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService, AdminServices adminServices) {
        super(adminServices);
        this.userService = userService;
    }

    @GetMapping("/users")
    public String index(Model model) {
        model.addAttribute(
                "users", userService.findAll()
        );
        model.addAttribute(
                "admin", adminServices.isAdmin()
        );
        return "admin/users/index";
    }

    @GetMapping({"/users/{id}"})
    public String show(@PathVariable("id") int id, Model model) {
        User user = userService.findOne(id);
        model.addAttribute("user",user);
        model.addAttribute("books",userService.getAllUserBook(user));
        model.addAttribute(
                "admin", adminServices.isAdmin()
        );
        return "admin/users/show";
    }

    @GetMapping("/users/new")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute(
                "admin", adminServices.isAdmin()
        );
        return "admin/users/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "admin/users/new";
        }
        userService.save(user);
        return "redirect:/users/";
    }

    @GetMapping({"/users/{id}/edit"})
    public String edit(Model model,@PathVariable("id") int id) {
        model.addAttribute("user",userService.findOne(id));
        model.addAttribute(
                "admin", adminServices.isAdmin()
        );
        model.addAttribute("enumRoles", Roles.values());
        return "admin/users/edit";
    }

    @PatchMapping("/users/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if(bindingResult.hasErrors()) {
            return "admin/users/edit";
        }
        userService.update(id,user);
        return "redirect:/admin/users/";
    }

    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin/users/";
    }

}
