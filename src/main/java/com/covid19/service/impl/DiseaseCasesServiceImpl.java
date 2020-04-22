package com.covid19.service.impl;

import com.covid19.common.ManipulateData;
import com.covid19.model.CovidCaseDetail;
import com.covid19.service.DiseaseCasesService;
import com.covid19.util.Constants;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
/**
 * @author Ayaz.Ahmed
 * @description A implementation class for reported cases.
 */
@Service
public class DiseaseCasesServiceImpl implements DiseaseCasesService {

    @Override
    public List<CovidCaseDetail> getReportedCases(){
        return ManipulateData.getLatestRegisteredCases();
    }

    @Override
    public List<CovidCaseDetail> getSortedReportedCases(){
        List<CovidCaseDetail> latestRegisteredCases = ManipulateData.getLatestRegisteredCases();

        Comparator<CovidCaseDetail> comparator = Comparator.comparingInt(CovidCaseDetail::getNoOfCases);
        Comparator<CovidCaseDetail> revComparator1 = comparator.reversed();

        Collections.sort(latestRegisteredCases, revComparator1);

        return latestRegisteredCases;
    }

    @Override
    public CovidCaseDetail getReportedCasesInCountry(final String countryName) throws Exception {
        List<CovidCaseDetail> latestRegisteredCases = ManipulateData.getLatestRegisteredCases();

        CovidCaseDetail resultUser = latestRegisteredCases.stream()
                .filter(input -> input.getCountry().equalsIgnoreCase(countryName))
                .findFirst().orElse(null);

        return resultUser;
    }

    @Override
    public List<CovidCaseDetail> getMostReportedNewCases() throws Exception {
        List<CovidCaseDetail> latestRegisteredCases = ManipulateData.getLatestRegisteredCases();

        Comparator<CovidCaseDetail> comparator = Comparator.comparingInt(CovidCaseDetail::getNoOfCases);
        Comparator<CovidCaseDetail> revComparator = comparator.reversed();

        Collections.sort(latestRegisteredCases, revComparator);
        return latestRegisteredCases.stream()
                .limit(Constants.TOP_FIVE_COUNTRY)
                .collect(Collectors.toList());
    }

    @Override
    public List<CovidCaseDetail> getReportedCasesSince(final String fromDate, final String toDate,
                                                       final String countryName) throws Exception {

        LocalDate dateFrom = LocalDate.parse(fromDate);
        LocalDate dateTo = LocalDate.parse(toDate);

        List<CovidCaseDetail> registeredCases = ManipulateData.getRegisteredCases();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yy");

          List<CovidCaseDetail> filteredData = registeredCases.stream()
                .filter(input -> input.getCountry().equalsIgnoreCase(countryName) &&
                        (LocalDate.parse(input.getDate(), formatter).isAfter(dateFrom) || LocalDate.parse(input.getDate(), formatter).isEqual(dateFrom)) &&
                        LocalDate.parse(input.getDate(), formatter).isAfter(dateTo) || LocalDate.parse(input.getDate(), formatter).isEqual(dateTo)
                ).collect(Collectors.toList());

          return filteredData;
    }
}
