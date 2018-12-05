package com.csye6225.fall2018.yuchen.resources;

import com.csye6225.fall2018.yuchen.datamodel.Announcement;
import com.csye6225.fall2018.yuchen.service.AnnouncementsService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

//../webapi/myresource
@Path("announcements")
public class AnnouncementsResource {

    AnnouncementsService announcementsService = new AnnouncementsService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Announcement> getCoursesByBoardId(@QueryParam("boardId") String boardId){
        if(boardId == null){
            return announcementsService.getAllAnnouncements();
        }
        return announcementsService.getAnnouncementByText(boardId);
    }

    @GET
    @Path("/{boardId}_{announcementId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Announcement getCourse(@PathParam("boardId") String boardId, @PathParam("announcementId") String announcementId){
        return announcementsService.getAnnouncement(boardId,announcementId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Announcement addCourse(Announcement announcementId){
        return  announcementsService.addAnnouncement(announcementId);
    }

    @DELETE
    @Path("/{boardId}_{announcementId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Announcement deleteCourse(@PathParam("boardId") String boardId, @PathParam("announcementId") String announcementId){
        return announcementsService.deleteAnnouncement(boardId,announcementId);
    }

    @PUT
    @Path("/{boardId}_{announcementId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Announcement updateCourse(@PathParam("boardId") String boardId, @PathParam("announcementId") String announcementId, Announcement announcement){
        return announcementsService.updatAnnouncementInformation(boardId,announcementId, announcement);
    }
}
