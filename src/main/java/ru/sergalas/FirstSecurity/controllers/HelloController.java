package ru.sergalas.FirstSecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.sergalas.FirstSecurity.security.PersonDetails;
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
    @ResponseBody
    public String showUserInfo()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        return personDetails.getUsername();
    }
}
