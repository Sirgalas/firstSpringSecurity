package ru.sergalas.FirstSecurity.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.sergalas.FirstSecurity.security.PersonDetails;
@Service
public class AdminServices {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void showUserInfo()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails =(PersonDetails) authentication.getPrincipal();
        System.out.println(personDetails.getPerson());
    }
}
