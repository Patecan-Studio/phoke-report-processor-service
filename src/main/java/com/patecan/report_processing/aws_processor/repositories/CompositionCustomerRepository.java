package com.patecan.report_processing.aws_processor.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.patecan.report_processing.aws_processor.data.entity.CompositionCustomerEntity;
import org.springframework.stereotype.Repository;


@Repository
public class CompositionCustomerRepository {
    final private DynamoDBMapper dynamoDBMapper;

    public CompositionCustomerRepository(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public void createCompositionCustomer(CompositionCustomerEntity compositionCustomerEntity){
        System.out.println(compositionCustomerEntity.toString());
        dynamoDBMapper.save(compositionCustomerEntity);
    }
}


