package com.csye6225.fall2018.yuchen.resources;

import com.csye6225.fall2018.yuchen.datamodel.Course;
import com.csye6225.fall2018.yuchen.datamodel.Program;
import com.csye6225.fall2018.yuchen.datamodel.Student;
import com.csye6225.fall2018.yuchen.service.CoursesService;
import com.csye6225.fall2018.yuchen.service.ProgramsService;
import com.csye6225.fall2018.yuchen.service.StudentsService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

//../webapi/programs
@Path("programs")
public class ProgramsResource {

    //api for programs edition
    ProgramsService programsService = new ProgramsService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Program> getPrograms() {
        return programsService.getAllPrograms();
    }

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

    //api for courses edition
    CoursesService coursesService = new CoursesService();

    @POST
    @Path("/{programId}/courses/{courseId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Program addCourse(@PathParam("programId")Long programId, @PathParam("courseId")Long courseId) {
        Course course = coursesService.getCourse(courseId);
        if(course!=null) {
            course.setProgramName(programsService.getProgram(programId).getProgramName());
            return programsService.addCourse(programId, courseId);
        }
        return null;
    }

    @DELETE
    @Path("/{programId}/courses/{courseId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Program deleteCourse(@PathParam("programId")Long programId, @PathParam("courseId") Long courseId) {
        Course course = coursesService.getCourse(courseId);
        Program program = programsService.getProgram(programId);
        if(course.getProgramName().equals(program.getProgramName())) {
            course.setProgramName("");
            return programsService.deleteCourse(programId, courseId);
        }
        return null;
    }

    @GET
    @Path("/{programId}/courses/{courseId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Course getCourseById(@PathParam("programId")Long programId, @PathParam("courseId") Long courseId) {
        Course course = coursesService.getCourse(courseId);
        Program program = programsService.getProgram(programId);
        if(course.getProgramName().equals(program.getProgramName()))
            return course;
        else return null;
    }

    @GET
    @Path("/{programId}/courses")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Course> getCourses(@PathParam("programId")Long programId) {
        Program program = programsService.getProgram(programId);
        List<Course> courseList = coursesService.getCoursesByProgram(program.getProgramName());
        return courseList;
    }

    //api for students edition
    StudentsService studentsService = new StudentsService();

    @POST
    @Path("/{programId}/students/{studId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Program addStudent(@PathParam("programId")Long programId, @PathParam("studId")Long studId) {
        Student student = studentsService.getStudent(studId);
        if(student!=null) {
            student.setProgramName(programsService.getProgram(programId).getProgramName());
            return programsService.addStudent(programId, studId);
        }
        return null;
    }

    @DELETE
    @Path("/{programId}/students/{studId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Program deleteStudent(@PathParam("programId")Long programId, @PathParam("studId") Long studId) {
        Student student = studentsService.getStudent(studId);
        Program program = programsService.getProgram(programId);
        if(student.getProgramName().equals(program.getProgramName())) {
            student.setProgramName("");
            return programsService.deleteStudent(programId, studId);
        }
        return null;
    }

    @GET
    @Path("/{programId}/students/{studId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Student getStudentById(@PathParam("programId")Long programId, @PathParam("studId") Long studId) {
        Student student = studentsService.getStudent(studId);
        Program program = programsService.getProgram(programId);
        if(student.getProgramName().equals(program.getProgramName()))
            return student;
        else return null;
    }

    @GET
    @Path("/{programId}/students")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Student> getStudents(@PathParam("programId")Long programId) {
        Program program = programsService.getProgram(programId);
        List<Student> studentList = studentsService.getStudentsByProgram(program.getProgramName());
        return studentList;
    }
}
