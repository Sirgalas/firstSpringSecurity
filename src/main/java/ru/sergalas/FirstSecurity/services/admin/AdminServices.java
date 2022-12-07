package ru.sergalas.FirstSecurity.services.admin;

import org.springframework.stereotype.Service;
import ru.sergalas.FirstSecurity.entities.users.entities.User;
import ru.sergalas.FirstSecurity.services.BaseUserService;

@Service
public class AdminServices extends BaseUserService {

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public boolean isAdmin()
    {
        return getAuthUser().getRole().equals(User.ADMIN);
    }
}
