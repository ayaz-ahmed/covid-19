package com.covid19.controller;

import com.covid19.model.CasesReport;
import com.covid19.service.DiseaseCasesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Ayaz.Ahmed
 * @description A endpoint class that will provide data about disease.
 */
@RestController
public class DiseaseController {

    @Autowired
    private DiseaseCasesService diseaseCasesService;

    @RequestMapping(value = "/newCases", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getAllNewCases() throws Exception {
        return ResponseEntity.ok(diseaseCasesService.getReportedCases());
    }

    @RequestMapping(value = "/sortedNewCases", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getSortedNewCases() throws Exception {
        return ResponseEntity.ok(diseaseCasesService.getSortedReportedCases());
    }

    @RequestMapping(value = "/newCasesInCountry/{countryName}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getNewCasesInCountry(@PathVariable("countryName") String countryName) throws Exception {
        return ResponseEntity.ok(diseaseCasesService.getReportedCasesInCountry(countryName));
    }

    @RequestMapping(value = "/mostReportedNewCases", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getMostReportedNewCases() throws Exception {
        return ResponseEntity.ok(diseaseCasesService.getMostReportedNewCases());
    }

    @RequestMapping(value = "/reportedCasesSince", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getReportedCasesSince(@Valid @RequestBody CasesReport casesReport) throws Exception {
        return ResponseEntity.ok(diseaseCasesService.getReportedCasesSince(casesReport.getFromDate(), casesReport.getToDate(), casesReport.getCountryName()));
    }
}
