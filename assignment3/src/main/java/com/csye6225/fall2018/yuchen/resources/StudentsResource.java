package com.csye6225.fall2018.yuchen.resources;

import com.csye6225.fall2018.yuchen.datamodel.Course;
import com.csye6225.fall2018.yuchen.datamodel.Student;
import com.csye6225.fall2018.yuchen.service.CoursesService;
import com.csye6225.fall2018.yuchen.service.SNSService;
import com.csye6225.fall2018.yuchen.service.StudentsService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//../webapi/myresource
@Path("students")
public class StudentsResource {

    StudentsService studentsService = new StudentsService();
    CoursesService coursesService = new CoursesService();
    SNSService snsService = new SNSService();

    //Students api
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

    //courses api
    @POST
    @Path("/{studentId}/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Student registerCourses(@PathParam("studentId") String studentId, List<String> registeredCourses){
        Student student = studentsService.registerCourses(studentId,registeredCourses);
        List<Course> courseList = coursesService.registerStudent(studentId, registeredCourses);
        snsService.registerStudent(student.getEmailId(), courseList);
        return student;
    }

    @DELETE
    @Path("/{studentId}/unregister")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Student unregisterCourses(@PathParam("studentId") String studentId, List<String> unregisteredCourses){
        Student student = studentsService.unregisterCourses(studentId,unregisteredCourses);
        List<Course> courseList = coursesService.unregisterStudent(studentId, unregisteredCourses);
        snsService.unregisterStudent(student.getEmailId(), courseList);
        return student;
    }

    /*@PUT
    @Path("/{studentId}/updateCourses")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Student updateCourses(@PathParam("studentId") String studentId, List<String> newCourses){
        List<String> list = studentsService.getStudent(studentId).getRegisteredCourses();
        if(list == null) list = new ArrayList<>();
        List<Course> oldCourses = new ArrayList<>();
        for(String courseId:list) oldCourses.add(coursesService.getCourse(courseId));
        Student student = studentsService.updateCourses(studentId,newCourses);
        List<Course> courseList = coursesService.updateStudent(studentId,list, newCourses);
        snsService.updateStudent(student.getEmailId(), oldCourses, courseList);
        return student;
    }*/
}
