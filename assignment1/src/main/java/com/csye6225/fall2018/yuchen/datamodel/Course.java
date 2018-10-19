package com.csye6225.fall2018.yuchen.datamodel;

import java.util.List;

public class Course {
    private long courseId;
    private String courseName;
    private String programName;
    private List<Lecture> lectureList;
    private String board;
    private String roster;
    private List<Long> enrolledStudents;
    private long ta;
    private long professor;

    public Course() {
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public List<Lecture> getLectureList() {
        return lectureList;
    }

    public void setLectureList(List<Lecture> lectureList) {
        this.lectureList = lectureList;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getRoster() {
        return roster;
    }

    public void setRoster(String roster) {
        this.roster = roster;
    }

    public List<Long> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(List<Long> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public long getTa() {
        return ta;
    }

    public void setTa(long ta) {
        this.ta = ta;
    }

    public long getProfessor() {
        return professor;
    }

    public void setProfessor(long professor) {
        this.professor = professor;
    }
}
