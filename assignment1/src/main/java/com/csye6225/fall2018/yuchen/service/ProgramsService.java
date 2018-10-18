package com.csye6225.fall2018.yuchen.service;

import com.csye6225.fall2018.yuchen.datamodel.InMemoryDatabase;
import com.csye6225.fall2018.yuchen.datamodel.Program;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProgramsService {

    HashMap<Long, Program> prog_Map = InMemoryDatabase.getProgramDB();

    public List<Program> getAllPrograms() {
        ArrayList<Program> list = new ArrayList<>();
        for(Program prog: prog_Map.values()){
            list.add(prog);
        }
        return list;
    }

    public Program addProgram(Program program){
        long nextAvailableId = prog_Map.size() + 1;
        program.setProgramId(nextAvailableId);
        prog_Map.put(nextAvailableId, program);

        return prog_Map.get(nextAvailableId);
    }

    public Program getProgram(Long progId){
        return prog_Map.get(progId);
    }

    public Program deleteProgram(Long progId){
        Program deletedProgDetails = prog_Map.get(progId);
        prog_Map.remove(progId);
        return deletedProgDetails;
    }

    public Program updateProgramInformation(Long progId, Program program){
        Program oldProgObject = prog_Map.get(progId);
        progId = oldProgObject.getProgramId();
        program.setProgramId(progId);
        prog_Map.put(progId, program);
        return program;
    }

}
