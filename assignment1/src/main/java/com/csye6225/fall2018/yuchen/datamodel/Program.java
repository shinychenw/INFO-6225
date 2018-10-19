package com.csye6225.fall2018.yuchen.datamodel;

import java.util.List;

public class Program {
    private List<Long> courseList;
    private List<Long> studentList;
    private String programName;
    private Long programId;

    public Program() {
    }


    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }


    public List<Long> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Long> courseList) {
        this.courseList = courseList;
    }

    public List<Long> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Long> studentList) {
        this.studentList = studentList;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }
}
