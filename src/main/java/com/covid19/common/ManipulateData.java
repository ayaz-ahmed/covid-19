package com.covid19.common;

import com.covid19.model.CovidCaseDetail;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Ayaz.Ahmed
 * @description A class that get information from csv file so no need to manipulate file
 * for every request.
 */
public class ManipulateData {

    private ManipulateData() {
    }

    private static final List<CovidCaseDetail> registeredCases = new ArrayList<>();

    public static void initializeRegisteredCases() throws Exception {
        CSVReader reader = new CSVReader(new FileReader(ManipulateData.class
                .getClassLoader().getResource(".")
                .getFile() + "/time_series_covid19_confirmed_global.csv"));

        List<String[]> allRows = reader.readAll();
        Supplier<Stream> stream = () -> allRows.stream();
        String[] headingRow = (String[]) stream.get().findFirst().get();

        stream.get().skip(1).forEach(input -> {
            String[] row = (String[]) input;
            AtomicInteger dateIndex = new AtomicInteger(4);

            Arrays.stream(row).skip(4).forEach(column -> {
                CovidCaseDetail covidCaseDetail = new CovidCaseDetail(row[0],
                        row[1], row[2], row[3],
                        Integer.valueOf(column),
                        headingRow[dateIndex.getAndIncrement()]);

                registeredCases.add(covidCaseDetail);
            });
        });
    }

    public static List<CovidCaseDetail> getLatestRegisteredCases() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yy");

        LocalDate latestDate = LocalDate.now().minusDays(1);
        return registeredCases.stream()
                .filter(input -> LocalDate.parse(input.getDate(), formatter).isEqual(latestDate))
                .collect(Collectors.toList());
    }

    public static List<CovidCaseDetail> getRegisteredCases() {
        return registeredCases;
    }
}
