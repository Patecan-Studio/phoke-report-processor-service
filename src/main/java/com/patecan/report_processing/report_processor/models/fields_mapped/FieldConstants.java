package com.patecan.report_processing.report_processor.models.fields_mapped;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class FieldConstants{
    @Value("${general_information_category}")
    public String GENERAL_INFORMATION_CATEGORY;


    @Value("${payment_information_category}")
    public String PAYMENT_INFORMATION_CATEGORY;

    /*

    general_information:
  order_code: "Mã hóa đơn"
  order_source: "Nguồn đơn"
  order_status: "Trạng thái đơn hàng"
  order_created_time: "Thời gian tạo đơn"
  order_paid_time: "Thời gian thanh toán"
  service_type: "Loại hình phục vụ"
  service_place: "Khu vực"
  table_number: "Bàn"
  cashier: "Thu ngân"
  shipping_partner: "Đối tác giao hàng"
  order_return: "Hoàn tiền của đơn"


payment_information:
  payment_total_price: "Tổng tiền hàng \n (1)"
  payment_vat: "Thuế VAT \n(2)"
  service_fee: "Phí dịch vụ \n(3)"
  total_discount: "Tổng giảm giá \n(4)"
  shipping_fee: "Phí GH thu khách \n(5)"
  total_pay: "Tổng tiền thanh toán \n(1 + 2 + 3 - 4 + 5)"
  payment_method: "Phương thức TT"
  tip: "Tiền Tip"


product_information:
  code: "Mã mặt hàng"
  name: "Tên mặt hàng, combo"
  category: "Danh mục"
  amount: "Số lượng"
  unit: "Đơn vị"
  price: "Giá bán"
  variant_name: "Tên lựa chọn"
  addon_price: "Giá lựa chọn thêm"
  percentage_discount_price: "% Giảm giá"
  total_discount: "Tổng giảm giá"
  total_price: "Tiền hàng"
     */

}
