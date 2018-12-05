package com.csye6225.fall2018.yuchen.resources;

import com.csye6225.fall2018.yuchen.datamodel.Student;
import com.csye6225.fall2018.yuchen.service.StudentsService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

//../webapi/myresource
@Path("students")
public class StudentsResource {

    StudentsService studentsService = new StudentsService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getStudentsByDepartment(@QueryParam("department") String department){
        if(department == null){
            return studentsService.getAllStudents();
        }
        return studentsService.getStudentsByDepartment(department);
    }

    @GET
    @Path("/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student getStudent(@PathParam("studentId") String studentId){
        return studentsService.getStudent(studentId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Student addStudent(Student student){
        return  studentsService.addStudent(student);
    }

    @DELETE
    @Path("/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Student deleteStudent(@PathParam("studentId") String studentId){
        return studentsService.deleteStudent(studentId);
    }

    @PUT
    @Path("/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Student updateStudent(@PathParam("studentId") String studentId, Student student){
        return studentsService.updateStudentInformation(studentId, student);
    }
}
