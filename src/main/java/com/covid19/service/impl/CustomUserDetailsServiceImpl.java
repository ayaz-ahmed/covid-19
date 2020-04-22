package com.covid19.service.impl;

import com.covid19.service.CustomUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
/**
 * @author Ayaz.Ahmed
 * @description A implementation class for user Details.
 */
@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    private static Map<String, String> authenticatedUsers;

    @PostConstruct
    public void init(){
        authenticatedUsers = new HashMap<>();
        authenticatedUsers.put("admin", "admin");
        authenticatedUsers.put("manager", "manager");
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        if (authenticatedUsers.containsKey(username)) {
            return new User(username, authenticatedUsers.get(username), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    @Override
    public List<com.covid19.model.User> getAuthenticatedUsers(){
        List<com.covid19.model.User> users = new ArrayList<>();
        authenticatedUsers.forEach((k,v) -> {
            users.add(new com.covid19.model.User(k, v));
        });

        return users;
    }
}
