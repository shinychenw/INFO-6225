package com.csye6225.fall2018.yuchen.resources;

import com.csye6225.fall2018.yuchen.datamodel.Professor;
import com.csye6225.fall2018.yuchen.service.ProfessorsService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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

    @GET
    @Path("/{professorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Professor getProfessor(@PathParam("professorId") String profId){
        return professorsService.getProfessor(profId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Professor addProfessor(Professor prof){
        return  professorsService.addProfessor(prof);
    }

    @DELETE
    @Path("/{professorId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Professor deleteProfessor(@PathParam("professorId") String profId){
        return professorsService.deleteProfessor(profId);
    }

    @PUT
    @Path("/{professorId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Professor updateProfessor(@PathParam("professorId") String profId, Professor prof){
        return professorsService.updateProfessorInformation(profId, prof);
    }
}
