package com.patecan.report_processing.report_processor.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.patecan.report_processing.report_processor.data.entity.OrderEntity;
import org.springframework.stereotype.Repository;


@Repository
public class OrderRepository{
    final private DynamoDBMapper dynamoDBMapper;

    public OrderRepository(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public String createOrder(OrderEntity orderEntity){
        System.out.println(orderEntity.toString());
        dynamoDBMapper.save(orderEntity);
        return "success";
    }
}


