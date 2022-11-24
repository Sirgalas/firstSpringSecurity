package ru.sergalas.FirstSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import ru.sergalas.FirstSecurity.security.AuthProviderImp;

import javax.persistence.Entity;
import java.net.Authenticator;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
public class SecurityConfig {
    private final AuthProviderImp authProvider;

    public SecurityConfig(AuthProviderImp authProvider)
    {
        this.authProvider = authProvider;
    }

    @Bean
    @Primary
    //настраивает аунтификатион
    protected AuthenticationManagerBuilder configure(AuthenticationManagerBuilder auth)
    {
        return auth.authenticationProvider(authProvider);
    }
}
