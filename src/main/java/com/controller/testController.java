package com.controller;

import com.service.CoursesService;
import com.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class testController {
    @Resource
    CoursesService coursesService;
    @Resource
    TeacherService teacherService;

    @RequestMapping(value = "testCourses")
    public void testCourses(){
        coursesService.selectAllName("",1);
    }
    @RequestMapping(value = "testTeacher")
    public void testTeacher(){
        teacherService.selectTeacherDetailByName(1,1);
    }
    @RequestMapping(value = "testTeacher1")
    public void testTeacher1(){
        teacherService.selectTeacherDetailByName(1,0);
    }
}
