package com.csye6225.fall2018.yuchen.datamodel;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.List;

@DynamoDBTable(tableName = "Course")
public class Course {
    private String id;
    private String courseId;
    private String professorId;
    private String taId;
    private String department;
    private String boardId;
    private List<String> roster;

    public Course(){

    }

    @DynamoDBAutoGeneratedKey
    @DynamoDBHashKey(attributeName = "Id")
    public String getId() {
        return id;
    }

    public void setId(String professorId) {
        this.id = professorId;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "CourseId", attributeName = "CourseId")
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @DynamoDBAttribute(attributeName = "Department")
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @DynamoDBAttribute(attributeName = "Roster")
    public List<String> getRoster() {
        return roster;
    }

    public void setRoster(List<String> roster) {
        this.roster = roster;
    }

    @DynamoDBAttribute(attributeName = "ProfessorId")
    public String getProfessorId() {
        return professorId;
    }

    public void setProfessorId(String professorId) {
        this.professorId = professorId;
    }

    @DynamoDBAttribute(attributeName = "TAId")
    public String getTaId() {
        return taId;
    }

    public void setTaId(String taId) {
        this.taId = taId;
    }

    @DynamoDBAttribute(attributeName = "BoardId")
    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    @DynamoDBIgnore
    @Override
    public String toString(){
        return "CourseId=" + getCourseId()+ ",ProfessorId=" + getProfessorId()+",Department="+getDepartment()+
                ",TAId="+getTaId() + ",BoardId=" + getBoardId();
    }
}
