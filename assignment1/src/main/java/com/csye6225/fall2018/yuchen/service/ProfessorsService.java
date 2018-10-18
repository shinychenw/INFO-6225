package com.csye6225.fall2018.yuchen.service;

import com.csye6225.fall2018.yuchen.datamodel.InMemoryDatabase;
import com.csye6225.fall2018.yuchen.datamodel.Professor;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ProfessorsService {

    HashMap<Long, Professor> prof_Map = InMemoryDatabase.getProfessorDB();

    public List<Professor> getAllProfessors() {
        //Getting the list
        ArrayList<Professor> list = new ArrayList<>();
        for(Professor prof: prof_Map.values()){
            list.add(prof);
        }
        return list;
    }

    public Professor addProfessor(Professor prof){
        long nextAvailableId = prof_Map.size() + 1;
        prof.setProfessorId(nextAvailableId);
        prof_Map.put(nextAvailableId, prof);

        return prof_Map.get(nextAvailableId);
    }

    //Getting a professor
    public Professor getProfessor(Long profId){
        return prof_Map.get(profId);
    }

    //Deleting
    public Professor deleteProfessor(Long profId){
        Professor deletedProfDetails = prof_Map.get(profId);
        prof_Map.remove(profId);
        return deletedProfDetails;
    }

    //Updating
    public Professor updateProfessorInformation(Long profId, Professor prof){
        Professor oldProfObject = prof_Map.get(profId);
        profId = oldProfObject.getProfessorId();
        prof.setProfessorId(profId);
        // Publishing New Values
        prof_Map.put(profId, prof);

        return prof;
    }

    //Get professors by department
    public List<Professor> getProfessorsByDepartment(String department){
        ArrayList<Professor> list = new ArrayList<>();
        for(Professor prof: prof_Map.values()){
            if(prof.getDepartment().equals(department))
                list.add(prof);
        }
        return list;
    }
}
