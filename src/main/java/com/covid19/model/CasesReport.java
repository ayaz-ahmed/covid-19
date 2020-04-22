package com.covid19.model;

import javax.validation.constraints.NotEmpty;

/**
 * @author Ayaz.Ahmed
 * @description A Dto class of request.
 */
public class CasesReport {

    @NotEmpty(message = "Please provide fromDate")
    private String fromDate;
    @NotEmpty(message = "Please provide toDate")
    private String toDate;
    @NotEmpty(message = "Please provide countryName")
    private String countryName;

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
