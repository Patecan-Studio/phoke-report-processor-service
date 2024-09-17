package com.patecan.report_processing.report_processor.data.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.patecan.report_processing.report_processor.schema.order.ThongTinChungSchema;
import com.patecan.report_processing.report_processor.schema.order.ThongTinMatHangSchema;
import com.patecan.report_processing.report_processor.schema.order.ThongTinThanhToanSchema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@DynamoDBTable(tableName = "order")
public class OrderEntity {
    @DynamoDBHashKey
    private String id;

    @DynamoDBAttribute(attributeName = "customerId")
    private String customerId;

    @DynamoDBAttribute(attributeName = "Thông tin mặt hàng")
    @JsonProperty("Thông tin mặt hàng")
    private ThongTinMatHangSchema thongTinMatHangSchema;

    @DynamoDBAttribute(attributeName = "Thông tin chung")
    @JsonProperty("Thông tin chung")
    private ThongTinChungSchema thongTinChung;

    @DynamoDBAttribute(attributeName = "Thông tin thanh toán")
    @JsonProperty("Thông tin thanh toán")
    private ThongTinThanhToanSchema thongTinThanhToan;
}


