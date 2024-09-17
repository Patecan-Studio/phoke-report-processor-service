package com.patecan;

import com.patecan.report_processing.MainProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.io.IOException;
import java.util.function.Function;

@SpringBootApplication
public class ReportProcessingApplication implements CommandLineRunner {


    @Autowired
    private MainProcessor mainProcessor;

    public static void main(String[] args) {
        SpringApplication.run(ReportProcessingApplication.class, args);
    }

    @Override
    public void run(String... args) throws IOException {
        mainProcessor.readExcelFile();
    }

    @Bean
    public Function<String, String> toUp() {
        return String::toUpperCase;
    }
}