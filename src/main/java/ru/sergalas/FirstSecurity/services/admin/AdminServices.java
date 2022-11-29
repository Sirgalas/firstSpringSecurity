package ru.sergalas.FirstSecurity.services.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.sergalas.FirstSecurity.entities.users.User;
import ru.sergalas.FirstSecurity.security.PersonDetails;
import ru.sergalas.FirstSecurity.services.BaseUserService;

@Service
public class AdminServices extends BaseUserService {

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public boolean isAdmin()
    {
        return getAuthUser().getRole().equals(User.ADMIN);
    }
}
