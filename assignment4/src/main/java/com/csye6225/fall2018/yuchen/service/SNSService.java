package com.csye6225.fall2018.yuchen.service;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.*;
import com.csye6225.fall2018.yuchen.datamodel.Course;
import com.csye6225.fall2018.yuchen.datamodel.SNSConnector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SNSService {
    static AmazonSNSClient snsClient;

    public SNSService() {
        snsClient = SNSConnector.getClient();
    }

    public String createTopic(String topicName) {
        //create a new SNS topic
        CreateTopicRequest createTopicRequest = new CreateTopicRequest(topicName);
        CreateTopicResult createTopicResult = snsClient.createTopic(createTopicRequest);
        //print TopicArn
//        System.out.println(createTopicResult);
        //get request id for CreateTopicRequest from SNS metadata
//        System.out.println("CreateTopicRequest - " + snsClient.getCachedResponseMetadata(createTopicRequest));
        return createTopicResult.getTopicArn();
    }

    public String deleteTopic(String topicArn){
        //delete an SNS topic
        DeleteTopicRequest deleteTopicRequest = new DeleteTopicRequest(topicArn);
        snsClient.deleteTopic(deleteTopicRequest);
        //get request id for DeleteTopicRequest from SNS metadata
        //System.out.println("DeleteTopicRequest - " + snsClient.getCachedResponseMetadata(deleteTopicRequest));
        return "DeleteTopicRequest - " + snsClient.getCachedResponseMetadata(deleteTopicRequest);
    }

    public String updateTopicName(String topicArn, String topicName){
        //modify an SNS topic name
        SetTopicAttributesRequest setTopicAttributesRequest = new SetTopicAttributesRequest(topicArn, "DisplayName", topicName);
        snsClient.setTopicAttributes(setTopicAttributesRequest);
        return "SetTopicAttributesRequest - " + snsClient.getCachedResponseMetadata(setTopicAttributesRequest);
    }

    public Map<String, String> getTopicAttributes(String topicArn){
        //get an SNS topic information
        GetTopicAttributesResult getTopicAttributesResult = snsClient.getTopicAttributes(topicArn);
        Map<String, String> result = getTopicAttributesResult.getAttributes();
        return result;
    }

    public String subscribe(String topicArn, String email) {
        //subscribe to an SNS topic
        SubscribeRequest subRequest = new SubscribeRequest(topicArn, "email", email);
        snsClient.subscribe(subRequest);
        //get request id for SubscribeRequest from SNS metadata
        //System.out.println("SubscribeRequest - " + snsClient.getCachedResponseMetadata(subRequest));
        System.out.println("Check your email and confirm subscription.");
        return "SubscribeRequest - " + snsClient.getCachedResponseMetadata(subRequest);
    }

    public String unsubscribe(String topicArn, String email) {
        String subscriptionArn = "";
        boolean flag = true;
        ListSubscriptionsByTopicResult listSubscriptionsByTopicResult= snsClient.listSubscriptionsByTopic(topicArn);
        for(Subscription subscription: listSubscriptionsByTopicResult.getSubscriptions()){
            if(subscription.getEndpoint().equals(email)) {
                subscriptionArn = subscription.getSubscriptionArn();
                flag = false;
            }
        }
        if(flag == true) return "no such email or topicArn";

        //unsubscribe to an SNS topic
        UnsubscribeRequest unsubRequest = new UnsubscribeRequest(subscriptionArn);
        snsClient.unsubscribe(unsubRequest);
        //get request id for SubscribeRequest from SNS metadata
        //System.out.println("SubscribeRequest - " + snsClient.getCachedResponseMetadata(subRequest));
        System.out.println("Check your email and confirm unsubscription.");
        return "unsubscribeRequest - " + snsClient.getCachedResponseMetadata(unsubRequest);
    }

    public String registerStudent(String emailId, List<Course> courseList){
        if(courseList == null) return "Success!";
        if(courseList.size() == 0) return "Success!";
        for(Course course: courseList){
            subscribe(course.getNotificationTopic(),emailId);
        }
        return "Success!";
    }

    public String unregisterStudent(String emailId, List<Course> courseList){
        if(courseList == null) return "Success!";
        if(courseList.size() == 0) return "Success!";
        for(Course course: courseList){
            unsubscribe(course.getNotificationTopic(),emailId);
        }
        return "Success!";
    }

    public String updateStudent(String emailId, List<Course> oldCourses, List<Course> newCourses){
        if(newCourses == null) return "Success!";
        if(newCourses.size() == 0) return "Success!";

        if(oldCourses == null) oldCourses = new ArrayList<>();
        for (Course n : newCourses) {
            for(Course o: oldCourses){
                if(n.getCourseId().equals(o.getCourseId())){
                    oldCourses.remove(o);
                    newCourses.remove(n);
                }
            }
        }
        for(Course course:newCourses){
            subscribe(course.getNotificationTopic(),emailId);
        }
        for(Course course:oldCourses){
            unsubscribe(course.getNotificationTopic(),emailId);
        }
        return "Success!";
    }
}
