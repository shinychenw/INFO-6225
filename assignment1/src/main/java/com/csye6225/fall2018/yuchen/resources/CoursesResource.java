package com.csye6225.fall2018.yuchen.resources;

import com.csye6225.fall2018.yuchen.datamodel.Course;
import com.csye6225.fall2018.yuchen.service.CoursesService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

//../webapi/courses
@Path("courses")
public class CoursesResource {
    CoursesService coursesService = new CoursesService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> getCoursesByProgram(@QueryParam("department") String programName){
        if(programName == null){
            return coursesService.getAllCourses();
        }
        return coursesService.getCoursesByProgram(programName);
    }

    @GET
    @Path("/{courseId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Course getCourse(@PathParam("courseId") Long courseId){
        return coursesService.getCourse(courseId);
    }

    @DELETE
    @Path("/{courseId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Course deleteCourse(@PathParam("courseId") long courseId){
        return coursesService.deleteCourse(courseId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Course addProfessor(Course course){
        return  coursesService.addCourse(course);
    }

    @PUT
    @Path("/{courseId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Course updateCourse(@PathParam("courseId") Long courseId, Course course){
        return coursesService.updateCourseInformation(courseId, course);
    }
}
