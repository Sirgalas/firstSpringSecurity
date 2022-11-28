package ru.sergalas.FirstSecurity.controllers.admin.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.sergalas.FirstSecurity.controllers.admin.BaseAdminController;

@Controller
public class UserController extends BaseAdminController {

    @GetMapping("/user/index")
    public String index()
    {
        return "admin/users/index";
    }
}
