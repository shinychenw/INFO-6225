package com.csye6225.fall2018.yuchen.resources;

import com.csye6225.fall2018.yuchen.datamodel.Course;
import com.csye6225.fall2018.yuchen.datamodel.Lecture;
import com.csye6225.fall2018.yuchen.datamodel.Program;
import com.csye6225.fall2018.yuchen.datamodel.Student;
import com.csye6225.fall2018.yuchen.service.CoursesService;
import com.csye6225.fall2018.yuchen.service.StudentsService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

//../webapi/courses
@Path("courses")
public class CoursesResource {

    //Course edition
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

    //Lectures edition
    @GET
    @Path("/{courseId}/lectures")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Lecture> getLectures(@QueryParam("courseId") Long courseId){
        return coursesService.getLectures(courseId);
    }

    @GET
    @Path("/{courseId}/lectures/{lectureId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Lecture getLectureById(@QueryParam("courseId") Long courseId,@QueryParam("lectureId") Long letureId){
        return coursesService.getLectureById(courseId,letureId);
    }

    @POST
    @Path("/{courseId}/lectures")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Lecture addLecture(@QueryParam("courseId") Long courseId, Lecture lecture) {
        return coursesService.addLecture(courseId,lecture);
    }

    @DELETE
    @Path("/{courseId}/lectures/{lectureId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Lecture updateLecture(@QueryParam("courseId") Long courseId, @QueryParam("lectureId") Long letureId) {
        return coursesService.deleteLecture(courseId,letureId);
    }


/*    //api for students edition
    StudentsService studentsService = new StudentsService();

    @POST
    @Path("/{courseId}/students/{studId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Course addStudent(@PathParam("courseId")Long courseId, @PathParam("studId")Long studId) {
        Student student = studentsService.getStudent(studId);
        if(student!=null) {
            student.getCoursesEnrolled().add(courseId);
            return coursesService.addStudent(courseId, studId);
        }
        return null;
    }

    @DELETE
    @Path("/{courseId}/students/{studId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Course deleteStudent(@PathParam("courseId")Long courseId, @PathParam("studId") Long studId) {
        Student student = studentsService.getStudent(studId);
        if(student.getCoursesEnrolled().contains(courseId)) {
            student.getCoursesEnrolled().remove(courseId);
            return coursesService.deleteStudent(courseId, studId);
        }
        return null;
    }

    @GET
    @Path("/{courseId}/students/{studId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Student getStudentById(@PathParam("courseId")Long courseId, @PathParam("studId") Long studId) {
        Student student = studentsService.getStudent(studId);
        Program program = programsService.getProgram(programId);
        if(student.getProgramName().equals(program.getProgramName()))
            return student;
        else return null;
    }

    @GET
    @Path("/{courseId}/students")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Student> getStudents(@PathParam("courseId")Long courseId) {
        Program program = programsService.getProgram(programId);
        List<Student> studentList = studentsService.getStudentsByProgram(program.getProgramName());
        return studentList;
    }*/
}
