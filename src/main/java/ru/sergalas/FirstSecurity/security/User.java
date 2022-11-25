package ru.sergalas.FirstSecurity.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class User implements UserDetails {
    private String userName;
    private String password;

    private String role;
    private boolean accessToRestrictedPolicy;

    public User(String name, String password)
    {
        userName  = name;
        this.password = password;
    }

    public User withAccessToRestrictedPolicy(boolean restrictedPolicy) {
        this.accessToRestrictedPolicy = restrictedPolicy;
        return this;
    }

    public boolean hasAccessToRestrictedPolicy() {
        return accessToRestrictedPolicy;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // SHOW_ACCOUNT, WITHDRAW_MONEY, SEND_MONEY
        // ROLE_ADMIN, ROLE_USER - это роли
        return Collections.singletonList(new SimpleGrantedAuthority("USER"));
    }


    public User withPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public User withUserName(String userName) {
        this.userName = userName;
        return this;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}