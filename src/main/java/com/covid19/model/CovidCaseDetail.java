package com.covid19.model;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Ayaz.Ahmed
 * @description A Dto class for disease
 */
public class CovidCaseDetail {
    private String state;
    private String country;
    private String latitude;
    private String longitude;
    private Integer noOfCases;
    private String date;

    public CovidCaseDetail(final String state, final String country, final String latitude,
                           final String longitude, final Integer noOfCases, final String date) {
        this.state = state;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.noOfCases = noOfCases;
        this.date = date;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Integer getNoOfCases() {
        return noOfCases;
    }

    public void setNoOfCases(Integer noOfCases) {
        this.noOfCases = noOfCases;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "CovidCaseDetail{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", noOfCases='" + noOfCases + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
