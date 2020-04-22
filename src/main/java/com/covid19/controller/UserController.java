package com.covid19.controller;

import com.covid19.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ayaz.Ahmed
 * @description A endpoint controller that provide users with password that can access
 * other endpoints.
 */
@RestController
public class UserController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getAuthenticatedUsers() throws Exception {
        return ResponseEntity.ok(customUserDetailsService.getAuthenticatedUsers());
    }
}
