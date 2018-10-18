package com.csye6225.fall2018.yuchen.service;

import com.csye6225.fall2018.yuchen.datamodel.InMemoryDatabase;
import com.csye6225.fall2018.yuchen.datamodel.Student;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class StudentsService {

    HashMap<Long, Student> stud_Map = InMemoryDatabase.getStudentDB();

    public List<Student> getAllStudents() {
        ArrayList<Student> list = new ArrayList<>();
        for(Student stud: stud_Map.values()){
            list.add(stud);
        }
        return list;
    }

    public Student addStudent(Student stud){
        long nextAvailableId = stud_Map.size() + 1;
        stud.setStudentId(nextAvailableId);
        stud_Map.put(nextAvailableId, stud);

        return stud_Map.get(nextAvailableId);
    }

    public Student getStudent(Long studId){
        return stud_Map.get(studId);
    }

    public Student deleteStudent(Long studId){
        Student deletedStudDetails = stud_Map.get(studId);
        stud_Map.remove(studId);
        return deletedStudDetails;
    }

    public Student updateStudentInformation(Long studId, Student stud){
        Student oldStudObject = stud_Map.get(studId);
        studId = oldStudObject.getStudentId();
        stud.setStudentId(studId);
        stud_Map.put(studId, stud);

        return stud;
    }

    public List<Student> getStudentsByProgram(String progName){
        ArrayList<Student> list = new ArrayList<>();
        for(Student stud: stud_Map.values()){
            if(stud.getProgramName().equals(progName))
                list.add(stud);
        }
        return list;
    }
}
