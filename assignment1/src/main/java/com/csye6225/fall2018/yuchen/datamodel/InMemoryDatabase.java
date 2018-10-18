package com.csye6225.fall2018.yuchen.datamodel;

import java.util.HashMap;

public class InMemoryDatabase {

    private static HashMap<Long, Professor> professorDB = new HashMap<>();
    private static HashMap<Long, Program> programDB = new HashMap<>();
    private static HashMap<Long, Student> studentDB = new HashMap<>();
    private static HashMap<Long, Course> courseDB = new HashMap<>();

    public static HashMap<Long, Professor> getProfessorDB() {
        return professorDB;
    }
    public static HashMap<Long, Program> getProgramDB() {return programDB;}
    public static HashMap<Long, Student> getStudentDB() {return studentDB;}
    public static HashMap<Long, Course> getCourseDB() {return courseDB;}
}
