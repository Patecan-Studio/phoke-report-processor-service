package com.patecan.report_processing.report_processor.schema.order;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DynamoDBDocument
public class ThongTinMatHangSchema {

    @DynamoDBAttribute(attributeName = "Mã mặt hàng")
    @JsonProperty("Mã mặt hàng")
    public String maMatHang;

    @DynamoDBAttribute(attributeName = "Tên mặt hàng, combo")
    @JsonProperty("Tên mặt hàng, combo")
    public String tenMatHangCombo;

    @DynamoDBAttribute(attributeName = "Danh mục")
    @JsonProperty("Danh mục")
    public String danhMuc;

    @DynamoDBAttribute(attributeName = "Số lượng")
    @JsonProperty("Số lượng")
    public double soLuong;

    @DynamoDBAttribute(attributeName = "Đơn vị")
    @JsonProperty("Đơn vị")
    public String donVi;

    @DynamoDBAttribute(attributeName = "Giá bán")
    @JsonProperty("Giá bán")
    public double giaBan;

    @DynamoDBAttribute(attributeName = "Tên lựa chọn")
    @JsonProperty("Tên lựa chọn")
    public String tenLuaChon;

    @DynamoDBAttribute(attributeName = "Giá lựa chọn thêm")
    @JsonProperty("Giá lựa chọn thêm")
    public double giaLuaChonThem;

    @DynamoDBAttribute(attributeName = "Phần trăm giảm giá")
    @JsonProperty("% Giảm giá")
    public double phanTramGiamGia;

    @DynamoDBAttribute(attributeName = "Tổng giảm giá")
    @JsonProperty("Tổng giảm giá")
    public double tongGiamGia;

    @DynamoDBAttribute(attributeName = "Tiền hàng")
    @JsonProperty("Tiền hàng")
    public double tienHang;

    // Add getters and setters

    @Override
    public String toString() {
        return "ThongTinMatHang{" +
                "maMatHang='" + maMatHang + '\'' +
                ", tenMatHangCombo='" + tenMatHangCombo + '\'' +
                ", danhMuc='" + danhMuc + '\'' +
                ", soLuong=" + soLuong +
                ", donVi='" + donVi + '\'' +
                ", giaBan=" + giaBan +
                ", tenLuaChon='" + tenLuaChon + '\'' +
                ", giaLuaChonThem=" + giaLuaChonThem +
                ", tongGiamGia=" + tongGiamGia +
                ", tienHang=" + tienHang +
                '}';
    }
}