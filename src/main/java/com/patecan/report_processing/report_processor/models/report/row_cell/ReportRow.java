package com.patecan.report_processing.report_processor.models.report.row_cell;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "report-row")
public class ReportRow {
    int headingCategoryRow;
    int attributeRow;
    int beginRecordRow;
}
