package com.csye6225.fall2018.yuchen.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.yuchen.datamodel.DynamoDBConnector;
import com.csye6225.fall2018.yuchen.datamodel.Course;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CoursesService {

    static DynamoDBConnector dynamoDB;
    DynamoDBMapper mapper;

    public CoursesService() {
        dynamoDB = new DynamoDBConnector();
        dynamoDB.init();
        mapper = new DynamoDBMapper(dynamoDB.getClient());
    }

    //Adding a course
    public Course addCourse(Course course){

        mapper.save(course);
        return course;
    }

    //Deleting
    public Course deleteCourse(String courseId){
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withS(courseId));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("CourseId = :val1").withExpressionAttributeValues(eav);

        Iterator<Course> scanResult = mapper.scan(Course.class, scanExpression).iterator();

        if(scanResult.hasNext() == false) return null;
        Course course =  scanResult.next();
        mapper.delete(course);
        return course;
    }

    //Updating
    public Course updateCourseInformation(String courseId, Course course){
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withS(courseId));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("CourseId = :val1").withExpressionAttributeValues(eav);

        Iterator<Course> scanResult = mapper.scan(Course.class, scanExpression).iterator();

        if(scanResult.hasNext() == false) return null;

        Course oldCourseObject =  scanResult.next();
        if(course.getCourseId()!=null) oldCourseObject.setCourseId(course.getCourseId());
        if(course.getDepartment()!=null) oldCourseObject.setDepartment(course.getDepartment());
        if(course.getBoardId()!=null) oldCourseObject.setBoardId(course.getBoardId());
        if(course.getProfessorId()!=null) oldCourseObject.setProfessorId(course.getProfessorId());
        if(course.getTaId()!=null) oldCourseObject.setTaId(course.getTaId());
        if(course.getRoster()!=null) oldCourseObject.setRoster(course.getRoster());

        mapper.save(oldCourseObject);

        return oldCourseObject;
    }

    // Getting a list of all courses
    public List<Course> getAllCourses() {
        //Getting the list
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withIndexName("CourseId")
                .withConsistentRead(false);

        List<Course> ilist = mapper.scan(Course.class, scanExpression);
        //Iterator<Course> indexItems = ilist.iterator();
        return ilist;
    }

    //Getting a course
    public Course getCourse(String courseId){

        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withS(courseId));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("CourseId = :val1").withExpressionAttributeValues(eav);

        Iterator<Course> scanResult = mapper.scan(Course.class, scanExpression).iterator();

        if(scanResult.hasNext() == false) return null;

        return scanResult.next();

        /*Course course = mapper.load(Course.class, courseId);
        return course;*/
    }

    //Get courses by department
    public List<Course> getCoursesByDepartment(String department){
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withS(department));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("department = :val1").withExpressionAttributeValues(eav);

        List<Course> scanResult = mapper.scan(Course.class, scanExpression);
        return scanResult;
    }
}
