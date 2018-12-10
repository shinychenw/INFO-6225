package com.csye6225.fall2018.yuchen.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.yuchen.datamodel.Course;
import com.csye6225.fall2018.yuchen.datamodel.DynamoDBConnector;

import java.util.*;

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
        if(course.getRoster() == null && course.getNotificationTopic() == null && course.getBoardId()==null){
            course.setRoster(new ArrayList<String>());
            course.setNotificationTopic(" ");
            course.setBoardId(" ");
        }
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
        if(course.getNotificationTopic()!=null) oldCourseObject.setNotificationTopic(course.getNotificationTopic());


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

    public List<Course> registerStudent(String studentId, List<String> registedCourses){
        List<Course> courseList = new ArrayList<>();
        if(registedCourses == null) return courseList;
        if(registedCourses.size() == 0) return courseList;
        for(String courseId:registedCourses){
            Course course = getCourse(courseId);
            if(course.getRoster()==null) course.setRoster(new ArrayList<String>());
            if(!course.getRoster().contains(studentId))
                course.getRoster().add(studentId);
            courseList.add(course);
            updateCourseInformation(courseId, course);
        }

        return courseList;
    }

    public List<Course> unregisterStudent(String studentId, List<String> unregistedCourses){
        List<Course> courseList = new ArrayList<>();
        if(unregistedCourses == null) return courseList;
        if(unregistedCourses.size() == 0) return courseList;
        for(String courseId:unregistedCourses){
            Course course = getCourse(courseId);
            if(course == null) continue;
            if(course.getRoster()!=null) course.getRoster().remove(studentId);
            courseList.add(course);
            updateCourseInformation(courseId, course);
        }
        return courseList;
    }

    public List<Course> updateStudent(String studentId,List<String> oldCourses, List<String> newCourses){
        if(newCourses == null) return new ArrayList<>();
        if(newCourses.size() == 0) return new ArrayList<>();

        List<Course> courseList = getAllCourses();
        for(Course course:courseList){
            if(course.getRoster()!=null) course.getRoster().remove(studentId);
            updateCourseInformation(course.getCourseId(), course);
        }
        courseList = new ArrayList<>();
        for(String courseId:newCourses){
            Course course = getCourse(courseId);
            if(course.getRoster()==null) course.setRoster(new ArrayList<String>());
            if(!course.getRoster().contains(studentId))
                course.getRoster().add(studentId);
            courseList.add(course);
            updateCourseInformation(courseId, course);
        }
        return courseList;
    }
}
