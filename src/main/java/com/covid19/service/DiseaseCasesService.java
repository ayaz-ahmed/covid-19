package com.covid19.service;

import com.covid19.model.CovidCaseDetail;

import java.util.List;
/**
 * @author Ayaz.Ahmed
 * @description A interface of Reported cases.
 */
public interface DiseaseCasesService {
    List<CovidCaseDetail> getReportedCases() throws Exception;
    List<CovidCaseDetail> getSortedReportedCases() throws Exception;
    CovidCaseDetail getReportedCasesInCountry(final String countryName) throws Exception;
    List<CovidCaseDetail> getMostReportedNewCases() throws Exception;
    List<CovidCaseDetail> getReportedCasesSince(final String fromDate, final String toDate, final String countryName) throws Exception;
}
