package com.csye6225.fall2018.yuchen.datamodel;

public class Program {
    private String[] courseList;
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

    public String[] getCourseList() {
        return courseList;
    }

    public void setCourseList(String[] courseList) {
        this.courseList = courseList;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }
}
