package com.csye6225.fall2018.yuchen.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.yuchen.datamodel.Board;
import com.csye6225.fall2018.yuchen.datamodel.DynamoDBConnector;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BoardsService {

    static DynamoDBConnector dynamoDB;
    DynamoDBMapper mapper;

    public BoardsService() {
        dynamoDB = new DynamoDBConnector();
        dynamoDB.init();
        mapper = new DynamoDBMapper(dynamoDB.getClient());
    }

    //Adding a course
    public Board addBoard(Board board){

        mapper.save(board);
        return board;
    }

    //Deleting
    public Board deleteBoard(String boardId){
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withS(boardId));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("BoardId = :val1").withExpressionAttributeValues(eav);

        Iterator<Board> scanResult = mapper.scan(Board.class, scanExpression).iterator();

        if(scanResult.hasNext() == false) return null;
        Board board =  scanResult.next();
        mapper.delete(board);
        return board;
    }

    //Updating
    public Board updateBoardInformation(String boardId, Board board){
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withS(boardId));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("BoardId = :val1").withExpressionAttributeValues(eav);

        Iterator<Board> scanResult = mapper.scan(Board.class, scanExpression).iterator();

        if(scanResult.hasNext() == false) return null;

        Board oldBoardObject =  scanResult.next();
        if(board.getCourseId()!=null) oldBoardObject.setCourseId(board.getCourseId());
        if(board.getBoardId()!=null) oldBoardObject.setBoardId(board.getBoardId());

        mapper.save(oldBoardObject);

        return oldBoardObject;
    }

    // Getting a list of all boards
    public List<Board> getAllBoards() {
        //Getting the list
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withIndexName("BoardId")
                .withConsistentRead(false);

        List<Board> ilist = mapper.scan(Board.class, scanExpression);
        //Iterator<Board> indexItems = ilist.iterator();
        return ilist;
    }

    //Getting a board
    public Board getBoard(String courseId){

        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withS(courseId));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("BoardId = :val1").withExpressionAttributeValues(eav);

        Iterator<Board> scanResult = mapper.scan(Board.class, scanExpression).iterator();

        if(scanResult.hasNext() == false) return null;

        return scanResult.next();

        /*Board board = mapper.load(Board.class, boardId);
        return board;*/
    }

    //Get the board by courseId
    public List<Board> getBoardByCourseId(String courseId){
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withS(courseId));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("CourseId = :val1").withExpressionAttributeValues(eav);

        List<Board> scanResult = mapper.scan(Board.class, scanExpression);
        return scanResult;
    }
}
