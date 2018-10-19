package com.csye6225.fall2018.yuchen.datamodel;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Professor {
    private String firstName;
    private String lastName;
    private String department;
    private long professorId;

    public Professor() {

    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(long professorId) {
        this.professorId = professorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
