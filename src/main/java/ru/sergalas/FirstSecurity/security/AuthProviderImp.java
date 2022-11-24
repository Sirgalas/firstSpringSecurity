package ru.sergalas.FirstSecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.sergalas.FirstSecurity.services.PersonDetailService;

import java.util.Collection;
import java.util.Collections;

@Component
public class AuthProviderImp implements AuthenticationProvider {
    private final PersonDetailService personDetailService;

    @Autowired
    public AuthProviderImp(PersonDetailService personDetailService)
    {
        this.personDetailService = personDetailService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
       String username = authentication.getName();

       UserDetails personDetails = personDetailService.loadUserByUsername(username);

       String password = authentication.getCredentials().toString();
       if(! password.equals(personDetails.getPassword())) {
           throw new BadCredentialsException("User not found or incorrect password");
       }
       return new UsernamePasswordAuthenticationToken(personDetails,password, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
