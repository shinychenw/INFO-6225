package com.csye6225.fall2018.yuchen.resources;

import com.csye6225.fall2018.yuchen.datamodel.Course;
import com.csye6225.fall2018.yuchen.service.CoursesService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

//../webapi/myresource
@Path("courses")
public class CoursesResource {

    CoursesService coursesService = new CoursesService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> getCoursesByDepartment(@QueryParam("department") String department){
        if(department == null){
            return coursesService.getAllCourses();
        }
        return coursesService.getCoursesByDepartment(department);
    }

    @GET
    @Path("/{courseId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Course getCourse(@PathParam("courseId") String courseId){
        return coursesService.getCourse(courseId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Course addCourse(Course course){
        return  coursesService.addCourse(course);
    }

    @DELETE
    @Path("/{courseId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Course deleteCourse(@PathParam("courseId") String courseId){
        return coursesService.deleteCourse(courseId);
    }

    @PUT
    @Path("/{courseId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Course updateCourse(@PathParam("courseId") String courseId, Course course){
        return coursesService.updateCourseInformation(courseId, course);
    }
}
