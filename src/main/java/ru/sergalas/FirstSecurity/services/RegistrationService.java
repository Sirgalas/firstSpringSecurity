package ru.sergalas.FirstSecurity.services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.sergalas.FirstSecurity.entities.users.entities.User;
import ru.sergalas.FirstSecurity.repositories.PeopleRepository;

import java.util.Optional;

@Service
public class RegistrationService {

    private final PeopleRepository peopleRepository;
    private final PasswordEncoder passwordEncoder;


    public RegistrationService(PeopleRepository peopleRepository, PasswordEncoder passwordEncoder) {
        this.peopleRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(User person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole("ROLE_USER");
        peopleRepository.save(person);
    }

    public boolean emptyUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> person = peopleRepository.findByUsername(username);
        if(person.isEmpty()) {
            return false;
        }
        return true;
    }
}
