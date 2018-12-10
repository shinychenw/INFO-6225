package com.csye6225.fall2018.yuchen.resources;


import com.csye6225.fall2018.yuchen.service.SNSService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

//../webapi/sns
@Path("sns")
public class SNSResource {
    SNSService snsService = new SNSService();

    @GET
    @Path("/createTopic")
    @Produces(MediaType.APPLICATION_JSON)
    public String createTopic(@QueryParam("topicname") String topicName){
        return snsService.createTopic(topicName);
    }

    @GET
    @Path("/deleteTopic")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteTopic(@QueryParam("topicarn") String topicArn){
        return snsService.deleteTopic(topicArn);
    }

    @GET
    @Path("/updateTopic")
    @Produces(MediaType.APPLICATION_JSON)
    public String updateTopicName(@QueryParam("topicarn") String topicArn, @QueryParam("topicname") String topicName){
        return snsService.updateTopicName(topicArn, topicName);
    }

    @GET
    @Path("/getTopicAttributes")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTopicAttributes(@QueryParam("topicarn") String topicArn){
        return snsService.getTopicAttributes(topicArn).get("DisplayName");
    }

    @GET
    @Path("/subscribe")
    @Produces(MediaType.APPLICATION_JSON)
    public String subscribe(@QueryParam("topicarn") String topicArn, @QueryParam("email") String email){
        return snsService.subscribe(topicArn, email);
    }

    @GET
    @Path("/unsubscribe")
    @Produces(MediaType.APPLICATION_JSON)
    public String unsubscribe(@QueryParam("topicarn") String topicArn, @QueryParam("email") String email){
        return snsService.unsubscribe(topicArn, email);
    }
}
