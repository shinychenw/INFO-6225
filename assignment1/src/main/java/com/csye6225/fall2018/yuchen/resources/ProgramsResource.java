package com.csye6225.fall2018.yuchen.resources;

import com.csye6225.fall2018.yuchen.datamodel.Program;
import com.csye6225.fall2018.yuchen.service.ProgramsService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

//../webapi/programs
@Path("programs")
public class ProgramsResource {

    ProgramsService programsService = new ProgramsService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Program> getPrograms() {
        return programsService.getAllPrograms();
    }

    // ... webapi/programs/1
    @GET
    @Path("/{programId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Program getProgram(@PathParam("programId") Long progId) {
        return programsService.getProgram(progId);
    }

    @DELETE
    @Path("/{programId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Program deleteProgram(@PathParam("programId") long progId) {
        return programsService.deleteProgram(progId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Program addProgram(Program program) {
        return programsService.addProgram(program);
    }

    @PUT
    @Path("/{programId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Program updateProgram(@PathParam("programId") Long progId, Program program) {
        return programsService.updateProgramInformation(progId, program);
    }
}
