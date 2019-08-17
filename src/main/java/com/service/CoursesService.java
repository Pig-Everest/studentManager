package com.service;

import com.pojo.Courses;

import java.util.List;
import java.util.Map;

public interface CoursesService {
    public Map<String,Object> selectAllName(String name, Integer page);
    public Map<String,Object> selectCoursesDetailByName(String name);
    public Map<String,Object> selectCoursesAll(Integer page);
    public void addCourses(Courses courses);
    public void updateCourses(Courses courses);
    public void deleteCourses(Integer coursesId);
    public Map<String,Object> selectCoursesById(Integer coursesId);
}
