package com.patecan.report_processing.report_processor.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patecan.report_processing.report_processor.data.entity.CustomerEntity;
import com.patecan.report_processing.report_processor.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;


@Service
public class DBCustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ObjectMapper objectMapper;  // Jackson ObjectMapper

    public CustomerEntity saveCustomerFromJson(String json, String orderId, String customerId) {
        try {
            // Deserialize the JSON string into the Order object
            CustomerEntity customer = objectMapper.readValue(json, CustomerEntity.class);
            customer.setId(customerId);
            customer.setOrderId(orderId);

            // Save the order to DynamoDB
            customerRepository.createCustomer(customer);

            return customer;
        } catch (Exception e) {
            // Handle parsing exception or save error
            e.printStackTrace();
        }
        return null;
    }
}
