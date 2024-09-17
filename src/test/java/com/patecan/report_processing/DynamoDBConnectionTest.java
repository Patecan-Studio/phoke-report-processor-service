package com.patecan.report_processing;


import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class DynamoDBConnectionTest {

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Test
    public void testDynamoDBConnection() {
        ListTablesResult result = amazonDynamoDB.listTables();
        assertNotNull(result);
        System.out.println("Tables: " + result.getTableNames());
    }
}
