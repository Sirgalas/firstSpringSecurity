package ru.sergalas.FirstSecurity.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.sergalas.FirstSecurity.entities.users.User;
import ru.sergalas.FirstSecurity.repositories.PeopleRepository;
import ru.sergalas.FirstSecurity.security.PersonDetails;

import java.util.Optional;

@Service
public class PersonDetailService implements UserDetailsService {

    private final PeopleRepository peopleRepository;

    public PersonDetailService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional <User> person = peopleRepository.findByUsername(username);
        if(person.isEmpty()) {
            throw new UsernameNotFoundException("User not found or incorrect password");
        }
        return new PersonDetails(person.get());
    }

}
