package com.covid19.model;

import javax.validation.constraints.NotEmpty;
/**
 * @author Ayaz.Ahmed
 * @description A Dto class for authentication.
 */
public class User {
    @NotEmpty(message = "Please provide username")
    private String username;
    @NotEmpty(message = "Please provide password")
    private String password;

    public User() { }

    public User(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
