package com.patecan.report_processing.report_processor.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.patecan.report_processing.report_processor.data.entity.CustomerEntity;
import org.springframework.stereotype.Repository;


@Repository
public class CustomerRepository {
    final private DynamoDBMapper dynamoDBMapper;

    public CustomerRepository(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public void createCustomer(CustomerEntity customerEntity){
        System.out.println(customerEntity.toString());
        dynamoDBMapper.save(customerEntity);
    }
}


