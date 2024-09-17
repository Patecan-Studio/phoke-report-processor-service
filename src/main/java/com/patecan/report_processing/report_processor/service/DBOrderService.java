package com.patecan.report_processing.report_processor.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patecan.report_processing.report_processor.data.entity.OrderEntity;
import com.patecan.report_processing.report_processor.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DBOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ObjectMapper objectMapper;  // Jackson ObjectMapper

    public OrderEntity saveOrderFromJson(String json, String customerId) {
        try {
            // Deserialize the JSON string into the Order object
            OrderEntity order = objectMapper.readValue(json, OrderEntity.class);
            String orderId = order.getThongTinChung().getMaHoaDon();
            order.setId(orderId);
            order.setCustomerId(customerId);

            // Save the order to DynamoDB
            orderRepository.createOrder(order);
            return order;
        } catch (Exception e) {
            // Handle parsing exception or save error
            e.printStackTrace();
        }
        return null;
    }
}
