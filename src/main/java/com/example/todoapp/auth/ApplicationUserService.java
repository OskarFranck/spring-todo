package com.example.todoapp.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class ApplicationUserService implements UserDetailsService{

    private ApplicationUserService applicationUserService;

    public ApplicationUserService user(ApplicationUserService applicationUserService) {
        return this.applicationUserService = applicationUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return applicationUserService.loadUserByUsername(username);
    }
}
