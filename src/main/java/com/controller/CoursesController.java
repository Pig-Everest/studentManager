package com.controller;

import com.pojo.Courses;
import com.service.CoursesService;
import com.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
public class CoursesController {
    @Resource
    CoursesService coursesService;
    @Resource
    TeacherService teacherService;

    @RequestMapping(value = "selectAllCourses/{page}")
    public ModelAndView selectAllCourses(ModelAndView modelAndView, String name, @PathVariable(value = "page") int page) {
        modelAndView.setViewName("coursesTable");
        Map<String, Object> map = coursesService.selectAllName(name, page);
        modelAndView.addObject("coursesList", map.get("coursesList"));
        modelAndView.addObject("pageInfo", map.get("pageInfo"));
        modelAndView.addObject("name", name);
        return modelAndView;
    }

    @RequestMapping(value = "selectDetailByName")
    public ModelAndView selectDetailByName(ModelAndView modelAndView, String name) {
        modelAndView.setViewName("coursesDetail");
        Map<String, Object> map = coursesService.selectCoursesDetailByName(name);
        modelAndView.addObject("coursesList", map.get("coursesList"));
        modelAndView.addObject("classesList", map.get("classesList"));
        modelAndView.addObject("name",name);
        return modelAndView;
    }

    @RequestMapping(value = "goToAddCourses")
    public ModelAndView goToAddCourses(ModelAndView modelAndView) {
        modelAndView.setViewName("addCourses");
        modelAndView.addObject("teacherList", teacherService.selectTeacherAllNoPage());
        return modelAndView;
    }

    @RequestMapping(value = "addCourses")
    public ModelAndView addCourses(ModelAndView modelAndView, Courses courses) {
        modelAndView.setViewName("redirect:selectAllCourses/1");
        coursesService.addCourses(courses);
        return modelAndView;
    }

    @RequestMapping(value = "goToUpdateCourses")
    public ModelAndView goToUpdateCourses(ModelAndView modelAndView, Integer coursesId) {
        modelAndView.setViewName("updateCourses");
        Map<String, Object> map = coursesService.selectCoursesById(coursesId);
        modelAndView.addObject("courses", map.get("courses"));
        modelAndView.addObject("teacher", map.get("teacher"));
        modelAndView.addObject("teacherList", teacherService.selectTeacherAllNoPage());
        return modelAndView;
    }

    @RequestMapping(value = "updateCourses")
    public ModelAndView updateCourses(ModelAndView modelAndView,Courses courses) {
        modelAndView.setViewName("redirect:selectAllCourses/1");
        coursesService.updateCourses(courses);
        return modelAndView;
    }

    @RequestMapping(value = "deleteCourses")
    public void deleteCourses(Integer coursesId) {
        coursesService.deleteCourses(coursesId);
    }
}
