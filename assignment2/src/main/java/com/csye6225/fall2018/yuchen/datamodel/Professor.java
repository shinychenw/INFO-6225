package com.csye6225.fall2018.yuchen.datamodel;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

@DynamoDBTable(tableName = "Professor")
public class Professor {
    private String id;
    private String professorId;
    private String firstName;
    private String lastName;
    private String joiningDate;
    private String department;

    public Professor(){

    }

    @DynamoDBAutoGeneratedKey
    @DynamoDBHashKey(attributeName = "Id")
    public String getId() {
        return id;
    }

    public void setId(String professorId) {
        this.id = professorId;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "ProfessorId", attributeName = "ProfessorId")
    public String getProfessorId() {
        return professorId;
    }

    public void setProfessorId(String professorId) {
        this.professorId = professorId;
    }

    @DynamoDBAttribute(attributeName = "FirstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @DynamoDBAttribute(attributeName = "LastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @DynamoDBAttribute(attributeName = "Department")
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @DynamoDBAttribute(attributeName = "JoiningDate")
    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate){
        this.joiningDate = joiningDate;
    }

    @DynamoDBIgnore
    @Override
    public String toString(){
        return "ProfessorId=" + getProfessorId()+ ",FirstName=" + getFirstName()+",Department="+getDepartment()+
                ",JoiningDate="+getJoiningDate();
    }
}
