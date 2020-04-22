package com.covid19.service;

import com.covid19.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author Ayaz.Ahmed
 * @description A interface of user detail.
 */
public interface CustomUserDetailsService extends UserDetailsService {
    List<User> getAuthenticatedUsers() throws Exception;
}
