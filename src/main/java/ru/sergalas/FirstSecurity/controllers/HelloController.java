package ru.sergalas.FirstSecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.sergalas.FirstSecurity.services.admin.AdminServices;

@Controller
public class HelloController {
    private final  AdminServices adminServices;
    @Autowired
    public HelloController(AdminServices adminServices) {
        this.adminServices = adminServices;
    }


    @GetMapping("/hello")
    public String sayHello(Model model) {
        model.addAttribute(
                "admin", adminServices.isAdmin()
        );
        return "site/hello";
    }

    @GetMapping("/showUserInfo")
    public String showUserInfo()
    {
        return "site/hello";
    }
}
