package com.csye6225.fall2018.yuchen.datamodel;


import java.util.List;

public class Student {
    private String firstName;
    private String lastName;
    private String programName;
    private String imageUri;
    private long studentId;
    private List<Long> coursesEnrolled;

    public Student() {
    }

    public boolean isEnrolled(Long courseId){
        if(coursesEnrolled.contains(courseId)) return true;
        return false;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public List<Long> getCoursesEnrolled() {
        return coursesEnrolled;
    }

    public void setCoursesEnrolled(List<Long> coursesEnrolled) {
        this.coursesEnrolled = coursesEnrolled;
    }
}
