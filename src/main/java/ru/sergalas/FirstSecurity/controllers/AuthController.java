package ru.sergalas.FirstSecurity.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.sergalas.FirstSecurity.entities.users.entities.User;
import ru.sergalas.FirstSecurity.entities.users.exeption.UserValidAuthException;
import ru.sergalas.FirstSecurity.entities.users.useCase.auth.dto.AuthDto;
import ru.sergalas.FirstSecurity.entities.users.util.UserErrorResponse;
import ru.sergalas.FirstSecurity.security.JWTUtil;
import ru.sergalas.FirstSecurity.services.RegistrationService;
import ru.sergalas.FirstSecurity.uitil.ErrorMapper;
import ru.sergalas.FirstSecurity.validator.PersonValidator;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final PersonValidator personValidator;
    private final RegistrationService registrationService;
    private final JWTUtil jwtUtil;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(
            PersonValidator personValidator,
            RegistrationService registrationService,
            JWTUtil jwtUtil,
            ModelMapper modelMapper,
            AuthenticationManager authenticationManager) {
        this.personValidator = personValidator;
        this.registrationService = registrationService;
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/registration")
    public Map<String, String> performRegistration(@RequestBody @Valid AuthDto personDTO, BindingResult bindingResult)
    {
        User person = this.modelMapper.map(personDTO,User.class);
        personValidator.validate(person, bindingResult);
        if(bindingResult.hasErrors()){
            List<FieldError> errors = bindingResult.getFieldErrors();
            throw new UserValidAuthException(ErrorMapper.map(errors));
        }
        registrationService.register(person);
        String token = jwtUtil.generateToken(person);
        return Map.of("token",token);
    }
    @PostMapping("/login")
    public Map<String, String> login (@RequestBody AuthDto personDto)
    {
        UsernamePasswordAuthenticationToken authenticationToken  =
                new UsernamePasswordAuthenticationToken(
                        personDto.getUsername(),
                        personDto.getPassword());
        try{
            authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            return  Map.of("message","Incorrect credentials");
        }
        User person = this.modelMapper.map(personDto,User.class);
        String token = jwtUtil.generateToken(person);
        return Map.of("token",token);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserValidAuthException e) {
        UserErrorResponse response = new UserErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
