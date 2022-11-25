package ru.sergalas.FirstSecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.sergalas.FirstSecurity.services.PersonDetailService;

import javax.persistence.Entity;
import java.net.Authenticator;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
public class SecurityConfig {
    private final PersonDetailService personDetailService;

    @Autowired
    public SecurityConfig(PersonDetailService personDetailService)
    {
        this.personDetailService = personDetailService;
    }

    @Bean
    @Primary
    //настраивает аунтификацию
    protected AuthenticationManagerBuilder configure(AuthenticationManagerBuilder auth) throws Exception {
        return auth.userDetailsService(personDetailService).and();
    }

    @Bean
    //конфигурируеам сам  SpringSecurity
    //и авторизацию
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/auth/login","/error").permitAll()
            .anyRequest().authenticated().and()
            .formLogin()
            .loginPage("/auth/login")
            .loginProcessingUrl("/process_login")
            .defaultSuccessUrl("/hello",true)
            .failureUrl("/auth/login?error");
        return http.build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder()
    {
        return NoOpPasswordEncoder.getInstance();
    }
}
