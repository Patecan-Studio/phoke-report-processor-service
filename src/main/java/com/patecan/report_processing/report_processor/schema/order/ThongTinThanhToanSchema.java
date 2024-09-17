package com.patecan.report_processing.report_processor.schema.order;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DynamoDBDocument
public class ThongTinThanhToanSchema {

    @DynamoDBAttribute(attributeName = "Tổng tiền hàng \n(1)")
    @JsonProperty("Tổng tiền hàng \n(1)")
    private double tongTienHang;

    @DynamoDBAttribute(attributeName = "Thuế VAT \n(2)")
    @JsonProperty("Thuế VAT \n(2)")
    private double thueVAT;

    @DynamoDBAttribute(attributeName = "Phí dịch vụ \n(3)")
    @JsonProperty("Phí dịch vụ \n(3)")
    private double phiDichVu;

    @DynamoDBAttribute(attributeName = "Tổng giảm giá \n(4)")
    @JsonProperty("Tổng giảm giá \n(4)")
    private double tongGiamGia;

    @DynamoDBAttribute(attributeName = "Phí GH thu khách \n(5)")
    @JsonProperty("Phí GH thu khách \n(5)")
    private double phiGHThuKhach;

    @DynamoDBAttribute(attributeName = "Tổng tiền thanh toán \n(1 + 2 + 3 - 4 + 5)")
    @JsonProperty("Tổng tiền thanh toán \n(1 + 2 + 3 - 4 + 5)")
    private double tongTienThanhToan;

    @DynamoDBAttribute(attributeName = "Phương thức TT")
    @JsonProperty("Phương thức TT")
    private String phuongThucTT;

    @DynamoDBAttribute(attributeName = "Tiền Tip")
    @JsonProperty("Tiền Tip")
    private double tienTip;

    // Getters and Setters

    @Override
    public String toString() {
        return "ThongTinThanhToanSchema{" +
                "tongTienHang=" + tongTienHang +
                ", thueVAT=" + thueVAT +
                ", phiDichVu=" + phiDichVu +
                ", tongGiamGia=" + tongGiamGia +
                ", phiGHThuKhach=" + phiGHThuKhach +
                ", tongTienThanhToan=" + tongTienThanhToan +
                ", phuongThucTT='" + phuongThucTT + '\'' +
                ", tienTip=" + tienTip +
                '}';
    }
}

