package ru.sergalas.FirstSecurity.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.sergalas.FirstSecurity.entities.users.entities.User;
import ru.sergalas.FirstSecurity.security.PersonDetails;

public class BaseUserService {

    protected User getAuthUser()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails =(PersonDetails) authentication.getPrincipal();
        return personDetails.getPerson();
    }
}
