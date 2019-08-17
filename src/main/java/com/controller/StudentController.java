package com.controller;

import com.pojo.Classes;
import com.pojo.Student;
import com.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
public class StudentController {
    @Resource
    StudentService studentService;

    @RequestMapping(value = "selectAllStudent/{page}")
    public ModelAndView selectAllStudent(ModelAndView modelAndView,String name, Integer num,@PathVariable(value = "page") Integer page){
        modelAndView.setViewName("studentManager");
        Map<String,Object> map = studentService.selectAllStudentByNameAndNum(name,num,page);
        modelAndView.addObject("studentList",map.get("studentList"));
        modelAndView.addObject("classesList",map.get("classesList"));
        modelAndView.addObject("pageInfo",map.get("pageInfo"));
        if (name==null||name==""){

        }else {
            modelAndView.addObject("name",name);
        }
        if (num==null||num==0){
            modelAndView.addObject("num",0);
        }else {
            modelAndView.addObject("num",num);
        }
        return modelAndView;
    }

    @RequestMapping(value = "goToAddStudent")
    public ModelAndView goToAddStudent(ModelAndView modelAndView){
        modelAndView.setViewName("addStudent");
        List<Classes> classesList = studentService.selectAllClasses();
        modelAndView.addObject("classesList",classesList);
        return modelAndView;
    }

    @RequestMapping(value = "insertStudent")
    public ModelAndView insertStudent(ModelAndView modelAndView, Student student){
        modelAndView.setViewName("redirect:selectAllStudent/1");
        studentService.insertStudent(student);
        return modelAndView;
    }

    @RequestMapping(value = "goToUpdateStudent")
    public ModelAndView goToUpdateStudent(ModelAndView modelAndView,Integer studentId){
        modelAndView.setViewName("updateStudent");
        Student student = studentService.selectStudentById(studentId);
        Classes classes = studentService.selectClassesByStudent(student);
        List<Classes> classesList = studentService.selectAllClasses();
        modelAndView.addObject("classesList",classesList);
        modelAndView.addObject("student",student);
        modelAndView.addObject("classes",classes);
        return modelAndView;
    }

    @RequestMapping(value = "updateStudent")
    public ModelAndView updateStudent(ModelAndView modelAndView,Student student){
        modelAndView.setViewName("redirect:selectAllStudent/1");
        studentService.updateStudent(student);
        return modelAndView;
    }

    @RequestMapping(value = "deleteStudent")
    public void deleteStudent(Integer studentId){
        studentService.deleteStudent(studentId);
    }
}
