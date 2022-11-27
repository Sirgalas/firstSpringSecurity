package ru.sergalas.FirstSecurity.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.sergalas.FirstSecurity.entities.Person;
import ru.sergalas.FirstSecurity.services.PersonDetailService;
import ru.sergalas.FirstSecurity.services.RegistrationService;

@Component
public class PersonValidator implements Validator {

    private final RegistrationService registrationService;

    public PersonValidator( RegistrationService registrationService)
    {
        this.registrationService = registrationService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);

    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if(registrationService.emptyUserByUsername(person.getUsername())){
            errors.rejectValue("username","","Username is exists");
        }
    }
}
