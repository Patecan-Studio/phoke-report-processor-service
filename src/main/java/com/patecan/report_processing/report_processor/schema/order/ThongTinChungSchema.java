package com.patecan.report_processing.report_processor.schema.order;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DynamoDBDocument
public class ThongTinChungSchema {

    @DynamoDBAttribute(attributeName = "Stt")
    @JsonProperty("Stt")
    private int stt;

    @DynamoDBAttribute(attributeName = "Mã hóa đơn")
    @JsonProperty("Mã hóa đơn")
    private String maHoaDon;

    @DynamoDBAttribute(attributeName = "Trạng thái đơn hàng")
    @JsonProperty("Trạng thái đơn hàng")
    private String trangThaiDonHang;

    @DynamoDBAttribute(attributeName = "Thời gian tạo đơn")
    @JsonProperty("Thời gian tạo đơn")
    private String thoiGianTaoDon;

    @DynamoDBAttribute(attributeName = "Thời gian thanh toán")
    @JsonProperty("Thời gian thanh toán")
    private String thoiGianThanhToan;

    @DynamoDBAttribute(attributeName = "Loại hình phục vụ")
    @JsonProperty("Loại hình phục vụ")
    private String loaiHinhPhucVu;

    @DynamoDBAttribute(attributeName = "Khu vực")
    @JsonProperty("Khu vực")
    private String khuVuc;

    @DynamoDBAttribute(attributeName = "Bàn")
    @JsonProperty("Bàn")
    private String ban;

    @DynamoDBAttribute(attributeName = "Thu ngân")
    @JsonProperty("Thu ngân")
    private String thuNgan;

    @DynamoDBAttribute(attributeName = "Nguồn đơn")
    @JsonProperty("Nguồn đơn")
    private String nguonDon;

    @DynamoDBAttribute(attributeName = "Đối tác giao hàng")
    @JsonProperty("Đối tác giao hàng")
    private String doiTacGiaoHang;

    @DynamoDBAttribute(attributeName = "Hoàn tiền của đơn")
    @JsonProperty("Hoàn tiền của đơn")
    private double hoanTienCuaDon;

    // Getters and Setters
}