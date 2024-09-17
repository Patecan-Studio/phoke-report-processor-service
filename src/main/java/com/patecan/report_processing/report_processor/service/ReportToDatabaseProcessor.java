package com.patecan.report_processing.report_processor.service;

import com.google.gson.Gson;
import com.patecan.report_processing.aws_processor.service.DBCompositionCustomerService;
import com.patecan.report_processing.report_processor.data.entity.CustomerEntity;
import com.patecan.report_processing.report_processor.data.entity.OrderEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class ReportToDatabaseProcessor {

    private final DBOrderService orderService;
    private final DBCustomerService customerService;
    private final DBCompositionCustomerService compositionCustomerService;

    public ReportToDatabaseProcessor(DBOrderService orderService, DBCustomerService customerService, DBCompositionCustomerService compositionCustomerService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.compositionCustomerService = compositionCustomerService;
    }


    public void processDataFromReportToDatabase( HashMap<Integer, Map<String, LinkedHashMap<String, Object>>> generalModelList, Integer rowNumber){
        String customerId = null;
        CustomerEntity customerEntity = null;


        Map<String, LinkedHashMap<String, Object>> entry = generalModelList.get(rowNumber);
        String json = new Gson().toJson(entry);
        System.out.println(((LinkedHashMap)entry.get("Thông tin chung")).get("Stt").toString());
        String orderId = ((LinkedHashMap)entry.get("Thông tin chung")).get("Mã hóa đơn").toString();

        LinkedHashMap<String, Object> customerEntry = entry.get("Thông tin khách hàng");


        if(customerEntry.get("Khách hàng") != null){
            customerId = "CUSTOMER"+"-" +(customerEntry.get("Số điện thoại") == null ? UUID.randomUUID().toString(): customerEntry.get("Số điện thoại"));
            String customerJson = new Gson().toJson(customerEntry);
            customerEntity = customerService.saveCustomerFromJson(customerJson, orderId, customerId);
        }



        OrderEntity orderEntity = orderService.saveOrderFromJson(json, customerId);


        if(customerEntity != null && orderEntity != null) {
            compositionCustomerService.createCompositionCustomer(customerEntity, orderEntity);
        }
    }

}
