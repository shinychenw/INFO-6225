package com.csye6225.fall2018.yuchen.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.yuchen.datamodel.DynamoDBConnector;
import com.csye6225.fall2018.yuchen.datamodel.Professor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ProfessorsService {

    static DynamoDBConnector dynamoDB;
    DynamoDBMapper mapper;

    public ProfessorsService() {
        dynamoDB = new DynamoDBConnector();
        dynamoDB.init();
        mapper = new DynamoDBMapper(dynamoDB.getClient());
    }

    //Adding a professor
    public Professor addProfessor(Professor prof){

        mapper.save(prof);
        return prof;
    }

    //Deleting
    public Professor deleteProfessor(String profId){
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withS(profId));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("ProfessorId = :val1").withExpressionAttributeValues(eav);

        Iterator<Professor> scanResult = mapper.scan(Professor.class, scanExpression).iterator();

        if(scanResult.hasNext() == false) return null;

        Professor prof =  scanResult.next();
        mapper.delete(prof);
        return prof;
    }

    //Updating
    public Professor updateProfessorInformation(String profId, Professor prof){
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withS(profId));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("ProfessorId = :val1").withExpressionAttributeValues(eav);

        Iterator<Professor> scanResult = mapper.scan(Professor.class, scanExpression).iterator();

        if(scanResult.hasNext() == false) return null;

        Professor oldProfObject =  scanResult.next();
        if(prof.getProfessorId()!=null) oldProfObject.setProfessorId(prof.getProfessorId());
        if(prof.getDepartment()!=null) oldProfObject.setDepartment(prof.getDepartment());
        if(prof.getFirstName()!=null) oldProfObject.setFirstName(prof.getFirstName());
        if(prof.getLastName()!=null) oldProfObject.setLastName(prof.getLastName());
        if(prof.getJoiningDate()!=null) oldProfObject.setJoiningDate(prof.getJoiningDate());

        mapper.save(oldProfObject);

        return oldProfObject;
    }

    // Getting a list of all professor
    // GET "..webapi/professors"
    public List<Professor> getAllProfessors() {
        //Getting the list
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withIndexName("ProfessorId")
                .withConsistentRead(false);

        List<Professor> ilist = mapper.scan(Professor.class, scanExpression);
        //Iterator<Professor> indexItems = ilist.iterator();
        return ilist;
    }

    //Getting a professor
    public Professor getProfessor(String profId){

        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withS(profId));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("ProfessorId = :val1").withExpressionAttributeValues(eav);

        Iterator<Professor> scanResult = mapper.scan(Professor.class, scanExpression).iterator();

        if(scanResult.hasNext() == false) return null;

        return scanResult.next();

        /*Professor prof = mapper.load(Professor.class, profId);
        return prof;*/
    }

    //Get professors by department
    public List<Professor> getProfessorsByDepartment(String department){
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withS(department));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("department = :val1").withExpressionAttributeValues(eav);

        List<Professor> scanResult = mapper.scan(Professor.class, scanExpression);
        return scanResult;
    }
}
