package com.covid19.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ayaz.Ahmed
 * @description A class that can be used to get application properties
 */
@Configuration
public class AppProperties {
    @Value("${covid.uri}")
    private String covidUri;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${csv.fileName}")
    private String csvFileName;

    public String getCovidUri() {
        return covidUri;
    }

    public String getJwtSecret() {
        return jwtSecret;
    }

    public String getCsvFileName() {
        return csvFileName;
    }
}
