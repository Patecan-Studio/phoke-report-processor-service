package com.patecan.report_processing.report_processor.models.report;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.patecan.report_processing.report_processor.models.report.categories.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
public class GeneralModel {


    Map<String, Object> details = new LinkedHashMap<>();

    @JsonAnySetter
    public void setDetail(String key, Object value) {
        details.putIfAbsent(key, value);
    }


    public static GeneralModel getObjectFromString(String categoryName) {
        return switch (categoryName) {
            case "thôngtinchung" -> new ThongTinChung();
            case "thôngtinmặthàng" -> new ThongTinMatHang();
            case "thôngtinthanhtoán" -> new ThongTinThanhToan();
            case "thôngtinkháchhàng" -> new ThongTinKhachHang();
            case "thôngtinchung_addition" -> new ThongTinChungAdditional();
            default -> new GeneralModel();
        };
    }
}
