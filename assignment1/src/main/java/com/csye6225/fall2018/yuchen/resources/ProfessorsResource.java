package com.csye6225.fall2018.yuchen.resources;

import com.csye6225.fall2018.yuchen.datamodel.Professor;
import com.csye6225.fall2018.yuchen.service.ProfessorsService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

//../webapi/myresource
@Path("professors")
public class ProfessorsResource {

    ProfessorsService professorsService = new ProfessorsService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Professor> getProfessorsByDepartment(@QueryParam("department") String department){
        if(department == null){
            return professorsService.getAllProfessors();
        }
        return professorsService.getProfessorsByDepartment(department);
    }

    // ... webapi/professor/1
    @GET
    @Path("/{professorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Professor getProfessor(@PathParam("professorId") Long profId){
        return professorsService.getProfessor(profId);
    }

    @DELETE
    @Path("/{professorId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Professor deleteProfessor(@PathParam("professorId") long profId){
        return professorsService.deleteProfessor(profId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Professor addProfessor(Professor prof){
        return  professorsService.addProfessor(prof);
    }

    @PUT
    @Path("/{professorId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Professor updateProfessor(@PathParam("professorId") Long profId, Professor prof){
        return professorsService.updateProfessorInformation(profId, prof);
    }
}
