package com.csye6225.fall2018.yuchen.datamodel;

import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

public class DynamoDBConnector {

    static AmazonDynamoDB dynamoDB;
    /*
        Init function to make the client available
        setup the resources
        resources include credentials
        and region
        build the clients
     */

    public static void init() {
        if(dynamoDB == null) {
            InstanceProfileCredentialsProvider credentialsProvider = new InstanceProfileCredentialsProvider(false);
            //ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();
            credentialsProvider.getCredentials();

            dynamoDB = AmazonDynamoDBClientBuilder
                    .standard()
                    .withCredentials(credentialsProvider)
                    .withRegion("us-east-2")
                    .build();
            System.out.println("I created the client");
        }

    }

    public AmazonDynamoDB getClient(){
        return dynamoDB;
    }

    /*public static void main(String[] args) throws Exception{
        init();
        String tableName = "students-test";
        // For intellij /Users/avinav/.aws/credentials
        *//*
            aws_access_key_id=AKIAJXS
            aws_secret_access_key=/+AgeULQdm6mRu0plx
         *//*
        GetItemRequest getItemRequest = new GetItemRequest();
        //Key that are you looking for: studentId with value 123
        Map<String, AttributeValue> itemToFetch = new HashMap<>();
        itemToFetch.put("studentId", new AttributeValue().withS("123"));
        getItemRequest.setKey(itemToFetch);
        // The table that we are looking at
        getItemRequest.setTableName("students-test");
        GetItemResult getItemResult = dynamoDB.getItem(getItemRequest);
        System.out.println("GetItemResult:" + getItemResult);
    }*/
}

