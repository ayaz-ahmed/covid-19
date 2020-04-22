
package com.covid19.controllerTest;

import com.covid19.Application;
import com.covid19.model.CasesReport;
import com.covid19.model.CovidCaseDetail;
import com.covid19.service.CustomUserDetailsService;
import com.covid19.util.TokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ayaz.Ahmed
 * @description A test class for reported issues.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
class DiseaseControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private ObjectMapper objectMapper;

    private String accessToken;

    @BeforeEach
    public void init() {
        if (StringUtils.isEmpty(accessToken)) {
            UserDetails userDetails = userDetailsService.loadUserByUsername("admin");
            accessToken = tokenProvider.generateToken(userDetails);
        }
    }

    @Test
    public void testGetAllNewCases() throws Exception {
        String URI = "/newCases";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).header("Authorization", "Bearer " + accessToken, accessToken).accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        CovidCaseDetail[] covidCaseDetail = objectMapper.readValue(response.getContentAsString(), CovidCaseDetail[].class);
        assertTrue(covidCaseDetail.length > 0);
    }

    @Test
    public void testGetSortedNewCases() throws Exception {
        String URI = "/sortedNewCases";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).header("Authorization", "Bearer " + accessToken, accessToken).accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        CovidCaseDetail[] covidCaseDetail = objectMapper.readValue(response.getContentAsString(), CovidCaseDetail[].class);
        assertTrue(covidCaseDetail.length > 0);
    }

    @Test
    public void testGetNewCasesInCountry() throws Exception {
        String URI = "/newCasesInCountry/US";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).header("Authorization", "Bearer " + accessToken, accessToken).accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        CovidCaseDetail covidCaseDetail = objectMapper.readValue(response.getContentAsString(), CovidCaseDetail.class);
        assertNotNull(covidCaseDetail);
    }

    @Test
    public void testGetMostReportedNewCases() throws Exception {
        String URI = "/mostReportedNewCases";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).header("Authorization", "Bearer " + accessToken, accessToken).accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        CovidCaseDetail[] covidCaseDetail = objectMapper.readValue(response.getContentAsString(), CovidCaseDetail[].class);
        assertTrue(covidCaseDetail.length > 0);
    }

    @Test
    public void testGetReportedCasesSince() throws Exception {
        String URI = "/reportedCasesSince";
        CasesReport casesReport = new CasesReport();
        casesReport.setCountryName("US");
        casesReport.setFromDate("2020-04-10");
        casesReport.setToDate("2020-04-16");

        String inputJson = objectMapper.writeValueAsString(casesReport);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson).header("Authorization", "Bearer " + accessToken, accessToken)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        CovidCaseDetail[] covidCaseDetail = objectMapper.readValue(response.getContentAsString(), CovidCaseDetail[].class);
        assertTrue(covidCaseDetail.length > 0);
    }
}
