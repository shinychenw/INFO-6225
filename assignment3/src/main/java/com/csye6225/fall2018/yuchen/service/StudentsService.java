package com.csye6225.fall2018.yuchen.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.yuchen.datamodel.DynamoDBConnector;
import com.csye6225.fall2018.yuchen.datamodel.Student;

import java.util.*;

public class StudentsService {

    static DynamoDBConnector dynamoDB;
    DynamoDBMapper mapper;

    public StudentsService() {
        dynamoDB = new DynamoDBConnector();
        dynamoDB.init();
        mapper = new DynamoDBMapper(dynamoDB.getClient());
    }

    //Adding a student
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
        if(student.getEmailId()!=null) oldStudObject.setEmailId(student.getEmailId());

        if(student.getRegisteredCourses()!=null) {
            int n = student.getRegisteredCourses().size();
            if(n > 3) {
                for (int i = 0; i < n-3 ; i++)
                    student.getRegisteredCourses().remove(i);
                System.out.println("this student's registedCourses is over the max limit of 3");
            }
            oldStudObject.setRegisteredCourses(student.getRegisteredCourses());
        }

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


    //Register courses
    public Student registerCourses(String studentId, List<String> registeredCourses){
        Student student = getStudent(studentId);
        if(registeredCourses == null) return student;
        if(registeredCourses.size() == 0) return student;
        if(student.getRegisteredCourses() == null) student.setRegisteredCourses(new ArrayList<String>());
        for(String courseId:registeredCourses){
            student.getRegisteredCourses().add(courseId);
        }
        Set set = new HashSet(student.getRegisteredCourses());
        student.getRegisteredCourses().clear();
        student.getRegisteredCourses().addAll(set);
        updateStudentInformation(studentId,student);
        return student;
    }

    //Unregister courses
    public Student unregisterCourses(String studentId, List<String> unregisteredCourses){
        Student student = getStudent(studentId);
        if(unregisteredCourses == null) return student;
        if(unregisteredCourses.size() == 0) return student;
        if(student.getRegisteredCourses() == null) return student;
        for(String courseId:unregisteredCourses){
            student.getRegisteredCourses().remove(courseId);
        }
        updateStudentInformation(studentId,student);
        return student;
    }

    //Update courses
    public Student updateCourses(String studentId, List<String> newCourses){
        Student student = getStudent(studentId);
        Set set = new HashSet(newCourses);
        List<String> list = new ArrayList<>(set);
        student.setRegisteredCourses(list);
        updateStudentInformation(studentId,student);
        return student;
    }
}
