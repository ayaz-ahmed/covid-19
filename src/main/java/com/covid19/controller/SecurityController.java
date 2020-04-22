package com.covid19.controller;

import com.covid19.model.User;
import com.covid19.service.impl.CustomUserDetailsServiceImpl;
import com.covid19.util.TokenProvider;
import com.covid19.model.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
/**
 * @author Ayaz.Ahmed
 * @description A endpoint class for user authentication and token generation.
 */
@RestController
public class SecurityController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private CustomUserDetailsServiceImpl userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> authenticate(@Valid @RequestBody User user) throws Exception {
        authenticate(user.getUsername(), user.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        String accessToken = tokenProvider.generateToken(userDetails);

        return ResponseEntity.ok(new TokenResponse(accessToken));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
