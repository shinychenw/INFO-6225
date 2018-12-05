package com.csye6225.fall2018.yuchen.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.yuchen.datamodel.DynamoDBConnector;
import com.csye6225.fall2018.yuchen.datamodel.Student;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class StudentsService {

    static DynamoDBConnector dynamoDB;
    DynamoDBMapper mapper;

    public StudentsService() {
        dynamoDB = new DynamoDBConnector();
        dynamoDB.init();
        mapper = new DynamoDBMapper(dynamoDB.getClient());
    }

    //Adding a professor
    public Student addStudent(Student student){

        mapper.save(student);
        return student;
    }

    //Deleting
    public Student deleteStudent(String studentId){
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withS(studentId));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("StudentId = :val1").withExpressionAttributeValues(eav);

        Iterator<Student> scanResult = mapper.scan(Student.class, scanExpression).iterator();

        if(scanResult.hasNext() == false) return null;

        Student student =  scanResult.next();
        mapper.delete(student);
        return student;
    }

    //Updating
    public Student updateStudentInformation(String studentId, Student student){
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withS(studentId));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("StudentId = :val1").withExpressionAttributeValues(eav);

        Iterator<Student> scanResult = mapper.scan(Student.class, scanExpression).iterator();

        if(scanResult.hasNext() == false) return null;

        Student oldStudObject =  scanResult.next();
        if(student.getStudentId()!=null) oldStudObject.setStudentId(student.getStudentId());
        if(student.getDepartment()!=null) oldStudObject.setDepartment(student.getDepartment());
        if(student.getFirstName()!=null) oldStudObject.setFirstName(student.getFirstName());
        if(student.getLastName()!=null) oldStudObject.setLastName(student.getLastName());
        if(student.getJoiningDate()!=null) oldStudObject.setJoiningDate(student.getJoiningDate());
        if(student.getRegisteredCourses()!=null) oldStudObject.setRegisteredCourses(student.getRegisteredCourses());

        mapper.save(oldStudObject);

        return oldStudObject;
    }

    // Getting a list of all students
    public List<Student> getAllStudents() {
        //Getting the list
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withIndexName("StudentId")
                .withConsistentRead(false);

        List<Student> ilist = mapper.scan(Student.class, scanExpression);
        //Iterator<Student> indexItems = ilist.iterator();
        return ilist;
    }

    //Getting a student
    public Student getStudent(String studentId){

        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withS(studentId));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("StudentId = :val1").withExpressionAttributeValues(eav);

        Iterator<Student> scanResult = mapper.scan(Student.class, scanExpression).iterator();

        if(scanResult.hasNext() == false) return null;

        return scanResult.next();

        /*Student student = mapper.load(Student.class, studentId);
        return student;*/
    }

    //Get students by department
    public List<Student> getStudentsByDepartment(String department){
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withS(department));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("department = :val1").withExpressionAttributeValues(eav);

        List<Student> scanResult = mapper.scan(Student.class, scanExpression);
        return scanResult;
    }
}
