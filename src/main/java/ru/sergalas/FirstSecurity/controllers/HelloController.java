package ru.sergalas.FirstSecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.sergalas.FirstSecurity.services.AdminServices;

@Controller
public class HelloController {
    private final  AdminServices adminServices;
    @Autowired
    public HelloController(AdminServices adminServices) {
        this.adminServices = adminServices;
    }


    @GetMapping("/hello")
    public String sayHello() {
        return "site/hello";
    }

    @GetMapping("/showUserInfo")
    public String showUserInfo()
    {
        adminServices.showUserInfo();
        return "site/hello";
    }
}
