package com.patecan.report_processing.common.utils;


import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeConverter {
    public static String convertReportTimeToFormattedTime(String timeFromReport){
        String originalDateTime = timeFromReport;

        // Step 1: Define the format of the input string
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");

        // Step 2: Parse the original string to a LocalDateTime object
        LocalDateTime localDateTime = LocalDateTime.parse(originalDateTime, inputFormatter);

        // Step 3: Convert LocalDateTime to ZonedDateTime in UTC (add a time zone if needed)
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneOffset.UTC);

        // Step 4: Format the ZonedDateTime to ISO 8601 format with milliseconds and 'Z' for UTC
        String isoDateTime = zonedDateTime.format(DateTimeFormatter.ISO_INSTANT);
        return isoDateTime;
    }
}
