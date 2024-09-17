package com.patecan.report_processing.aws_processor.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.patecan.report_processing.aws_processor.data.entity.CompositionCustomerEntity;
import com.patecan.report_processing.aws_processor.repositories.CompositionCustomerRepository;
import com.patecan.report_processing.common.utils.TimeConverter;
import com.patecan.report_processing.report_processor.data.entity.CustomerEntity;
import com.patecan.report_processing.report_processor.data.entity.OrderEntity;
import com.patecan.report_processing.report_processor.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DBCompositionCustomerService {

    @Autowired
    private CompositionCustomerRepository compositionCustomerRepository;

    @Autowired
    private ObjectMapper objectMapper;  // Jackson ObjectMapper

    public void createCompositionCustomer(CustomerEntity customerEntity, OrderEntity orderEntity) {
        CompositionCustomerEntity customerCompositionEntity = new CompositionCustomerEntity();

        String name = customerEntity.getCustomerName();
        String phoneNumber = customerEntity.getCustomerPhoneNumber();
        String mostRecentOrderFood = orderEntity.getThongTinMatHangSchema().getTenMatHangCombo();
        String mostRecentOrderTime = orderEntity.getThongTinChung().getThoiGianTaoDon();

        String customerId = "CUSTOMER" + "-" + phoneNumber;

        customerCompositionEntity.setId(customerId);
        customerCompositionEntity.setCustomerSortKey(customerId);
        customerCompositionEntity.setCustomerName(name);
        customerCompositionEntity.setCustomerNamePrefix((name.toLowerCase().split(" ")[0]).substring(0, 2));
        customerCompositionEntity.setPhoneNumber(phoneNumber);
        customerCompositionEntity.setMostRecentOrderFood(mostRecentOrderFood);
        customerCompositionEntity.setMostRecentOrderTime(TimeConverter.convertReportTimeToFormattedTime(mostRecentOrderTime));

        compositionCustomerRepository.createCompositionCustomer(customerCompositionEntity);

    }


//    public void saveCustomerFromJson(String json, String orderId, UUID customerId) {
//        try {
//            // Deserialize the JSON string into the Order object
//            CustomerEntity customer = objectMapper.readValue(json, CustomerEntity.class);
//            customer.setId(customerId);
//            customer.setOrderId(orderId);
//
//            // Save the order to DynamoDB
//            customerRepository.createCustomer(customer);
//        } catch (Exception e) {
//            // Handle parsing exception or save error
//            e.printStackTrace();
//        }
//    }
}
