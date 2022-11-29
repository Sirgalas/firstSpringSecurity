package ru.sergalas.FirstSecurity.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sergalas.FirstSecurity.services.admin.AdminServices;


@RequestMapping("/admin")
@Controller
public class BaseAdminController {

    public final AdminServices adminServices;

    @Autowired
    public BaseAdminController(AdminServices adminServices) {
        this.adminServices = adminServices;
    }
}
