package com.controller;

import com.pojo.Courses;
import com.pojo.Teacher;
import com.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class TeacherController {
    @Resource
    TeacherService teacherService;

    @RequestMapping(value = "selectAllTeacher/{page}")
    public ModelAndView selectAllTeacher(ModelAndView modelAndView, @PathVariable(value = "page") int page, String name) {
        modelAndView.setViewName("teacherTable");
        Map<String, Object> map = teacherService.selectTeacherAll(page, name);
        modelAndView.addObject("pageInfo", map.get("pageInfo"));
        modelAndView.addObject("teacherList", map.get("teacherList"));
        return modelAndView;
    }

    //淘汰的方法
    /*@RequestMapping(value = "selectTeacherDetail")
    public ModelAndView selectTeacherDetail(ModelAndView modelAndView,Integer id){
        modelAndView.setViewName("teacherDetail");
        Map<String,Object> map = teacherService.selectTeacherDetailById(id);
        modelAndView.addObject("coursesList",map.get("coursesList"));
        modelAndView.addObject("classesList",map.get("classesList"));
        return modelAndView;
    }*/

    @RequestMapping(value = "selectTeacherDetailByName/{id}")
    public ModelAndView selectTeacherDetailByName(ModelAndView modelAndView, @PathVariable(value = "id") Integer id, Integer classesId) {
        modelAndView.setViewName("teacherDetail");
        Map<String, Object> map = teacherService.selectTeacherDetailByName(id, classesId);
        Teacher teacher = teacherService.selectTeacherById(id);
        modelAndView.addObject("coursesList", map.get("coursesList"));
        modelAndView.addObject("classesList", map.get("classesList"));
        modelAndView.addObject("classes", map.get("classes"));
        modelAndView.addObject("id", id);
        modelAndView.addObject("teacher",teacher);
        modelAndView.addObject("courseNames", teacherService.selectCourseNameByTeacherId(id));
        return modelAndView;
    }

    @RequestMapping(value = "insertTeacher")
    public ModelAndView insertTeacher(ModelAndView modelAndView, Teacher teacher) {
        modelAndView.setViewName("redirect:selectAllTeacher/1");
        teacherService.insertTeacher(teacher);
        return modelAndView;
    }

    @RequestMapping(value = "goToUpdateTeacher")
    public ModelAndView goToUpdateTeacher(ModelAndView modelAndView, Integer teacherId) {
        modelAndView.setViewName("updateTeacher");
        modelAndView.addObject("teacher", teacherService.selectTeacherById(teacherId));
        return modelAndView;
    }

    @RequestMapping(value = "updateTeacher")
    public ModelAndView updateTeacher(ModelAndView modelAndView, Teacher teacher) {
        modelAndView.setViewName("redirect:selectAllTeacher/1");
        teacherService.updateTeacher(teacher);
        return modelAndView;
    }

    @RequestMapping(value = "deleteTeacher")
    public void deleteTeacher(Integer teacherId) {
        teacherService.deleteTeacher(teacherId);
    }

    @RequestMapping(value = "goToUpdateTeacherDetail")
    public ModelAndView goToUpdateTeacherDetail(ModelAndView modelAndView, String coursesName, Integer[] coursesIds) {
        modelAndView.setViewName("updateTeacherDetail");
        List<Integer> list = new ArrayList<>();
        for (Integer i : coursesIds
        ) {
            list.add(i);
        }
        Map<String, Object> map = teacherService.selectCoursesByCoursesName(coursesName, list);
        modelAndView.addObject("coursesList", map.get("coursesList"));
        modelAndView.addObject("name", map.get("name"));
        return modelAndView;
    }

    @RequestMapping(value = "updateTeacherDetail")
    public ModelAndView updateTeacherDetail(ModelAndView modelAndView, String coursesName, Integer[] coursesIds) {
        modelAndView.setViewName("redirect:selectAllTeacher/1");
        List<Integer> list = new ArrayList<>();
        for (Integer i : coursesIds
        ) {
            list.add(i);
        }
        teacherService.updateTeacherDetailById(list, coursesName);
        return modelAndView;
    }

    @RequestMapping(value = "deleteTeacherDetail")
    public ModelAndView deleteTeacherDetail(ModelAndView modelAndView, String coursesName, Integer[] coursesIds) {
        modelAndView.setViewName("redirect:selectAllTeacher/1");
        List<Integer> list = new ArrayList<>();
        for (Integer i : coursesIds
        ) {
            list.add(i);
        }
        Map<String, Object> map = teacherService.selectCoursesByCoursesName(coursesName, list);
        List<Courses> coursesList = (List<Courses>) map.get("coursesList");
        List<Integer> ids = new ArrayList<>();
        for (Courses courses : coursesList
        ) {
            ids.add(courses.getCoursesid());
        }
        teacherService.deleteTeacherDetailByIds(ids);
        return modelAndView;
    }
}
