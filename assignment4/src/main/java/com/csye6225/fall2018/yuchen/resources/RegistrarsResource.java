package com.csye6225.fall2018.yuchen.resources;

import com.csye6225.fall2018.yuchen.datamodel.Board;
import com.csye6225.fall2018.yuchen.datamodel.Registrar;
import com.csye6225.fall2018.yuchen.service.BoardsService;
import com.csye6225.fall2018.yuchen.service.RegistrarsService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

//../webapi/myresource
@Path("registrars")
public class RegistrarsResource {

    RegistrarsService registrarsService = new RegistrarsService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Registrar> getRegistrarsByOfferingType(@QueryParam("offeringType") String offeringType){
        if(offeringType == null){
            return registrarsService.getAllRegistrars();
        }
        return registrarsService.getRegistrarByOfferingType(offeringType);
    }

    @GET
    @Path("/{offeringId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Registrar getRegistrar(@PathParam("offeringId") String offeringId){
        return registrarsService.getRegistrar(offeringId);
    }

    @POST
    @Path("/registerOffering")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Registrar addRegistrar(Registrar registrar){
        return  registrarsService.addRegistrar(registrar);
    }

    @DELETE
    @Path("/{offeringId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Registrar deleteRegistrar(@PathParam("offeringId") String offeringId){
        return registrarsService.deleteRegistrar(offeringId);
    }

    @PUT
    @Path("/{offeringId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Registrar updateRegistrar(@PathParam("offeringId") String offeringId, Registrar registrar){
        return registrarsService.updateRegistrarInformation(offeringId, registrar);
    }
}
