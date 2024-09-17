package com.patecan.report_processing.report_processor.data.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@DynamoDBTable(tableName = "customer")
public class CustomerEntity {
    @DynamoDBHashKey
    private String id;

    private String orderId;

    @DynamoDBAttribute(attributeName = "Khách hàng")
    @JsonProperty("Khách hàng")
    String customerName = null;

    @DynamoDBAttribute(attributeName = "Số điện thoại")
    @JsonProperty("Số điện thoại")
    String customerPhoneNumber = null;


    @DynamoDBAttribute(attributeName = "Thẻ thành viên")
    @JsonProperty("Thẻ thành viên")
    String customerMembershipCard = null;


    @DynamoDBAttribute(attributeName = "Điạ chỉ giao hàng")
    @JsonProperty("Điạ chỉ giao hàng")
    String shippingAddress = "";


    @DynamoDBAttribute(attributeName = "Phường xã")
    @JsonProperty("Phường xã")
    String ward = "";


    @DynamoDBAttribute(attributeName = "Quận huyện")
    @JsonProperty("Quận huyện")
    String district = "";


    @DynamoDBAttribute(attributeName = "Tỉnh thành phố")
    @JsonProperty("Tỉnh thành phố")
    String city = "";


    @DynamoDBAttribute(attributeName = "Số người")
    @JsonProperty("Số người")
    int customerAmount = 1;


    @DynamoDBAttribute(attributeName = "Ghi chú khách hàng")
    @JsonProperty("Ghi chú khách hàng")
    String customerNote = "";

}


