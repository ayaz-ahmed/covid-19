package com.covid19.model;

/**
 * @author Ayaz.Ahmed
 * @description A Dto class from token response.
 */
public class TokenResponse {
    private final String accessToken;

    public TokenResponse(final String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
