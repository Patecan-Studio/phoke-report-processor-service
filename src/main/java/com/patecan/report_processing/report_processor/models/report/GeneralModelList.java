package com.patecan.report_processing.report_processor.models.report;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class GeneralModelList {
    HashMap<Integer, Map<String,LinkedHashMap<String, Object>>> generalModelList;

    public GeneralModelList() {
        this.generalModelList = new HashMap<>();
        // Step 2: Initialize a nested Map
        Map<String, LinkedHashMap<String, Object>> innerMap = new HashMap<>();
        // Step 3: Initialize the ArrayList for GeneralModel
        LinkedHashMap<String, Object> generalModels = new LinkedHashMap<String, Object>();
        // Example: Add a GeneralModel instance to the ArrayList
        //generalModels..add(new LinkedHashMap());
        // Step 4: Add the ArrayList to the inner map with a key
        innerMap.put("ExampleKey", generalModels);

        // Step 5: Add the inner map to the outer HashMap with an Integer key
        generalModelList.put(-1, innerMap);
    }


    public void processAllInToJson(){
        ObjectMapper objectMapper = new ObjectMapper();
        LinkedHashMap<String, Object> orderData = new LinkedHashMap<>();
        LinkedHashMap<String, Object> customerData = new LinkedHashMap<>();

        try {
            String json = objectMapper.writeValueAsString(generalModelList);
            JsonNode rootNode = objectMapper.readTree(json);
            generalModelList.forEach((rowNum,entry) -> {
                entry.forEach((categoryName, data) -> {

                });
            });

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void print(){
        generalModelList.forEach((rowNum,entry) -> {
            System.out.println("--------------------------------- ROW " + rowNum+" ---------------------------------");
            entry.forEach((categoryName, data) -> {
                System.out.println("\t CATEGORY: " + categoryName);
                data.forEach( (atrName, atrVal) -> {
                    System.out.println("\t\t|_______ " + atrName+ ": " + atrVal);
                });
            });

        });
    }
}
