package com.controller;

import com.pojo.Classes;
import com.pojo.Teacher;
import com.service.ClassService;
import com.service.CoursesService;
import com.service.TimeTableService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;

@Controller
public class TimeTableController {
    @Resource
    TimeTableService timeTableService;
    @Resource
    CoursesService coursesService;
    @Resource
    ClassService classService;

    @RequestMapping(value = "selectTimeTable/{page}/{id}")
    public ModelAndView selectTimeTable(ModelAndView modelAndView, @PathVariable(value = "page")int page,
                                        @PathVariable(value = "id")int id, Teacher teacher){
        modelAndView.setViewName("timeTable");
        Classes classes = classService.selectClassesById(id);
        Map<String,Object> map = timeTableService.selectTimeTableByClassesId(id,page,teacher);
        modelAndView.addObject("id",id);
        modelAndView.addObject("coursesList",map.get("coursesList"));
        modelAndView.addObject("teachers",map.get("teachers"));//用于表中数据的部分teacher数组
        modelAndView.addObject("pageInfo",map.get("pageInfo"));
        modelAndView.addObject("teacherNames",map
        .get("teacherNames"));//用于选项框的teacher数组
        modelAndView.addObject("classes",classes);
        modelAndView.addObject("teacher", map.get("teacher"));//用于返回选择的教师的单个teacher类
        return modelAndView;
    }

    @RequestMapping(value = "insertTimeTable")
    public void insertTimeTable(int classesId,int coursesId){
        timeTableService.addTimeTable(classesId,coursesId);
    }

    @RequestMapping(value = "goToTimeTableAdd/{page}")
    public ModelAndView goToTimeTableAdd(ModelAndView modelAndView,int classesId,@PathVariable(value = "page") int page){
        modelAndView.setViewName("addTimeTable");
        Map<String,Object> map = coursesService.selectCoursesAll(page);
        modelAndView.addObject("id",classesId);
        modelAndView.addObject("coursesList",map.get("coursesList"));
        modelAndView.addObject("teacherList",map.get("teacherList"));
        modelAndView.addObject("pageInfo",map.get("pageInfo"));
        return modelAndView;
    }

    @RequestMapping(value = "deleteTimeTable")
    public void deleteTimeTable(Integer classesId,Integer coursesId){
        timeTableService.deleteTimeTable(classesId,coursesId);
    }
}
