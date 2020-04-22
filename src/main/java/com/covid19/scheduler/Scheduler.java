package com.covid19.scheduler;

import com.covid19.app.AppProperties;
import com.covid19.common.ManipulateData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Ayaz.Ahmed
 * @description A scheduler that execute when application starts to execute as well as
 * 8 Am. which will get data from uri and and save that data in csv file.
 */
@Component
public class Scheduler {

    @Autowired
    private AppProperties appProperties;


    private static final Logger LOGGER = LoggerFactory.getLogger(Scheduler.class);

    //second, minute, hour, day of month, month, day(s) of week
    @Scheduled(cron = "0 0 8 * * *")
    @PostConstruct
    public void init() {

        try {
            String url = appProperties.getCovidUri();
            HttpEntity entity;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            entity = new HttpEntity<>(headers);
            ResponseEntity responseEntity = new RestTemplate().exchange(url,
                    HttpMethod.GET, entity, String.class);

            String csvData = (String) responseEntity.getBody();

            File file = new File(getClass().getClassLoader().getResource(".").getFile() + "/" + appProperties.getCsvFileName());
            Files.write(Paths.get(file.getPath()), csvData.getBytes());

            ManipulateData.initializeRegisteredCases();

        } catch (Exception e) {
            LOGGER.error("Exception ::" + e);
        }
    }
}
