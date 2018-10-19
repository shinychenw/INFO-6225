package com.csye6225.fall2018.yuchen.service;

import com.csye6225.fall2018.yuchen.datamodel.InMemoryDatabase;
import com.csye6225.fall2018.yuchen.datamodel.Course;
import com.csye6225.fall2018.yuchen.datamodel.Lecture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CoursesService {
    HashMap<Long, Course> course_Map = InMemoryDatabase.getCourseDB();

    public List<Course> getAllCourses() {
        ArrayList<Course> list = new ArrayList<>();
        for(Course course: course_Map.values()){
            list.add(course);
        }
        return list;
    }

    public Course addCourse(Course course){
        long nextAvailableId = course_Map.size() + 1;
        course.setCourseId(nextAvailableId);
        course.setEnrolledStudents(new ArrayList<Long>());
        course.setLectureList(new ArrayList<Lecture>() );
        course_Map.put(nextAvailableId, course);
        return course_Map.get(nextAvailableId);
    }

    public Course getCourse(Long courseId){
        return course_Map.get(courseId);
    }

    public Course deleteCourse(Long courseId){
        Course deletedCourseDetails = course_Map.get(courseId);
        course_Map.remove(courseId);
        return deletedCourseDetails;
    }

    public Course updateCourseInformation(Long courseId, Course course){
        Course oldCourseObject = course_Map.get(courseId);
        courseId = oldCourseObject.getCourseId();
        course.setCourseId(courseId);
        course_Map.put(courseId, course);

        return course;
    }

    public List<Course> getCoursesByProgram(String programName){
        ArrayList<Course> list = new ArrayList<>();
        for(Course course: course_Map.values()){
            if(course.getProgramName().equals(programName))
                list.add(course);
        }
        return list;
    }

    public List<Lecture> getLectures(Long courseId){
        Course course = course_Map.get(courseId);
        List<Lecture> lectureList = course.getLectureList();
        return lectureList;
    }

    public Lecture getLectureById(Long courseId, Long lectureId){
        Course course = course_Map.get(courseId);
        for(Lecture l:course.getLectureList()){
            if(l.getLectureId() == lectureId)
                return l;
        }
        return null;
    }

    public Lecture addLecture(Long courseId, Lecture lecture){
        Course course = course_Map.get(courseId);
        course.getLectureList().add(lecture);
        return lecture;
    }

    //bug
    public Lecture deleteLecture(Long courseId, Long lectureId){
        Course course = course_Map.get(courseId);
        for(Lecture l:course.getLectureList()){
            if(l.getLectureId()==lectureId) {
                course.getLectureList().remove(l);
                return l;
            }
        }
        return null;
    }
}
