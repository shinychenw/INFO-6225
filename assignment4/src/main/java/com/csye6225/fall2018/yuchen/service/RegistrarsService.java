package com.csye6225.fall2018.yuchen.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.yuchen.datamodel.Registrar;
import com.csye6225.fall2018.yuchen.datamodel.DynamoDBConnector;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RegistrarsService {

    static DynamoDBConnector dynamoDB;
    DynamoDBMapper mapper;

    public RegistrarsService() {
        dynamoDB = new DynamoDBConnector();
        dynamoDB.init();
        mapper = new DynamoDBMapper(dynamoDB.getClient());
    }

    //Adding a registrar
    public Registrar addRegistrar(Registrar registrar){
        mapper.save(registrar);
        return registrar;
    }

    //Deleting
    public Registrar deleteRegistrar(String offeringId){
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withS(offeringId));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("OfferingId = :val1").withExpressionAttributeValues(eav);

        Iterator<Registrar> scanResult = mapper.scan(Registrar.class, scanExpression).iterator();

        if(scanResult.hasNext() == false) return null;
        Registrar registrar =  scanResult.next();
        mapper.delete(registrar);
        return registrar;
    }

    //Updating
    public Registrar updateRegistrarInformation(String offeringId, Registrar registrar){
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withS(offeringId));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("OfferingId = :val1").withExpressionAttributeValues(eav);

        Iterator<Registrar> scanResult = mapper.scan(Registrar.class, scanExpression).iterator();

        if(scanResult.hasNext() == false) return null;

        Registrar oldRegistrarObject =  scanResult.next();
        if(registrar.getOfferingId()!=null) oldRegistrarObject.setOfferingId(registrar.getOfferingId());
        if(registrar.getOfferingType()!=null) oldRegistrarObject.setOfferingType(registrar.getOfferingType());
        if(registrar.getPerUnitPrice()!=null) oldRegistrarObject.setPerUnitPrice(registrar.getPerUnitPrice());

        mapper.save(oldRegistrarObject);

        return oldRegistrarObject;
    }

    // Getting a list of all boards
    public List<Registrar> getAllRegistrars() {
        //Getting the list
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withIndexName("OfferingId")
                .withConsistentRead(false);

        List<Registrar> ilist = mapper.scan(Registrar.class, scanExpression);
        //Iterator<Board> indexItems = ilist.iterator();
        return ilist;
    }

    //Getting a board
    public Registrar getRegistrar(String offeringId){

        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withS(offeringId));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("OfferingId = :val1").withExpressionAttributeValues(eav);

        Iterator<Registrar> scanResult = mapper.scan(Registrar.class, scanExpression).iterator();

        if(scanResult.hasNext() == false) return null;

        return scanResult.next();

        /*Registrar registrar = mapper.load(Registrar.class, offeringId);
        return registrar;*/
    }

    //Get the registrar by offeringType
    public List<Registrar> getRegistrarByOfferingType(String offeringType){
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withS(offeringType));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("OfferingType = :val1").withExpressionAttributeValues(eav);

        List<Registrar> scanResult = mapper.scan(Registrar.class, scanExpression);
        return scanResult;
    }
}
