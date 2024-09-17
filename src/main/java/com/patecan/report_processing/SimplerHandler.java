package com.patecan.report_processing;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class SimplerHandler implements RequestHandler<String, String> {

    public String handleRequest(String input, Context context){
        LambdaLogger logger = context.getLogger();
        logger.log("SimplerHandler");
        return input.toUpperCase();
    }
}
