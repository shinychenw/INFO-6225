package com.csye6225.fall2018.yuchen.datamodel;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;

public class SNSConnector {
    static AmazonSNSClient snsClient;

    public static AmazonSNSClient getClient(){
        if(snsClient == null){
            //InstanceProfileCredentialsProvider credentialsProvider = new InstanceProfileCredentialsProvider(false);
            ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();
            credentialsProvider.getCredentials();

            //create a new SNS client and set endpoint
            /*snsClient = new AmazonSNSClient(credentialsProvider);
            snsClient.setRegion(Region.getRegion(Regions.US_EAST_2));*/

            AmazonSNSClientBuilder clientBuilder = AmazonSNSClientBuilder.standard();
            clientBuilder.setCredentials(credentialsProvider);
            clientBuilder.setRegion(Regions.US_EAST_2.getName());
            snsClient = (AmazonSNSClient) clientBuilder.build();
            System.out.println("I created the SNSClient");
        }
        return snsClient;
    }

}
