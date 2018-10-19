package com.csye6225.fall2018.yuchen.resources;

import com.csye6225.fall2018.yuchen.datamodel.Student;
import com.csye6225.fall2018.yuchen.service.StudentsService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

//../webapi/students
@Path("students")
public class StudentsResource {
    StudentsService studentsService = new StudentsService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getStudentsByProgram(@QueryParam("program") String program, @QueryParam("courseId") Long courseId){
        if(program != null){
            return studentsService.getStudentsByProgram(program);
        }
        if(courseId != null){
            return studentsService.getStudentsByCourseId(courseId);
        }
        return studentsService.getAllStudents();
    }

    // ... webapi/students/1
    @GET
    @Path("/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student getStudent(@PathParam("studentId") Long studId){
        return studentsService.getStudent(studId);
    }

    @DELETE
    @Path("/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Student deleteStudent(@PathParam("studentId") long studId){
        return studentsService.deleteStudent(studId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Student addStudent(Student stud){
        return  studentsService.addStudent(stud);
    }

    @PUT
    @Path("/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Student updateStudent(@PathParam("studentId") Long studId, Student stud){
        return studentsService.updateStudentInformation(studId, stud);
    }
}
