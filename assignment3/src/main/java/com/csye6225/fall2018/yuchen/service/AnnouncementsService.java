package com.csye6225.fall2018.yuchen.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.yuchen.datamodel.Announcement;
import com.csye6225.fall2018.yuchen.datamodel.DynamoDBConnector;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AnnouncementsService {

    static DynamoDBConnector dynamoDB;
    DynamoDBMapper mapper;

    public AnnouncementsService() {
        dynamoDB = new DynamoDBConnector();
        dynamoDB.init();
        mapper = new DynamoDBMapper(dynamoDB.getClient());
    }

    //Adding a course
    public Announcement addAnnouncement(Announcement board){

        mapper.save(board);
        return board;
    }

    //Deleting
    public Announcement deleteAnnouncement(String boardId, String announcementId){
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withS(boardId));
        eav.put(":val2", new AttributeValue().withS(announcementId));
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("BoardId = :val1 and AnnouncementId = :val2").withExpressionAttributeValues(eav);

        Iterator<Announcement> scanResult = mapper.scan(Announcement.class, scanExpression).iterator();

        if(scanResult.hasNext() == false) return null;
        Announcement announcement =  scanResult.next();
        mapper.delete(announcement);
        return announcement;
    }

    //Updating
    public Announcement updatAnnouncementInformation(String boardId, String announcementId, Announcement announcement){
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withS(boardId));
        eav.put(":val2", new AttributeValue().withS(announcementId));
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("BoardId = :val1 and AnnouncementId = :val2").withExpressionAttributeValues(eav);

        Iterator<Announcement> scanResult = mapper.scan(Announcement.class, scanExpression).iterator();

        if(scanResult.hasNext() == false) return null;

        Announcement oldAnnouncementObject =  scanResult.next();
        if(announcement.getAnnouncementText()!=null) oldAnnouncementObject.setAnnouncementText(announcement.getAnnouncementText());
        if(announcement.getBoardId()!=null) oldAnnouncementObject.setBoardId(announcement.getBoardId());
        if(announcement.getAnnouncementId()!=null) oldAnnouncementObject.setAnnouncementId(announcement.getAnnouncementId());

        mapper.save(oldAnnouncementObject);

        return oldAnnouncementObject;
    }

    // Getting a list of all announcements
    public List<Announcement> getAllAnnouncements() {
        //Getting the list
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withIndexName("Announcement-Index")
                .withConsistentRead(false);

        List<Announcement> ilist = mapper.scan(Announcement.class, scanExpression);
        //Iterator<Announcement> indexItems = ilist.iterator();
        return ilist;
    }

    //Getting a announcement
    public Announcement getAnnouncement(String boardId, String announcementId){

        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withS(boardId));
        eav.put(":val2", new AttributeValue().withS(announcementId));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("BoardId = :val1 and AnnouncementId = :val2").withExpressionAttributeValues(eav);

        Iterator<Announcement> scanResult = mapper.scan(Announcement.class, scanExpression).iterator();

        if(scanResult.hasNext() == false) return null;

        return scanResult.next();

        /*Announcement announcement = mapper.load(Announcement.class, announcementId);
        return announcement;*/
    }

    //Get the announcement by boardId
    public List<Announcement> getAnnouncementByText(String boardId){
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withS(boardId));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("BoardId = :val1").withExpressionAttributeValues(eav);

        List<Announcement> scanResult = mapper.scan(Announcement.class, scanExpression);
        return scanResult;
    }
}
