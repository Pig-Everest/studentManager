package com.controller;

import com.github.pagehelper.PageInfo;
import com.pojo.Classes;
import com.service.ClassService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
public class ClassesController {
    @Resource
    ClassService classService;

    @RequestMapping(value = "selectClasses/{page}")
    public ModelAndView selectClasses(ModelAndView modelAndView,String name,@PathVariable(value = "page") int page){
        modelAndView.setViewName("classTable");
        PageInfo pageInfo = classService.selectCLasses(page,name);
        System.out.println(pageInfo);
        modelAndView.addObject("list",pageInfo.getList());
        modelAndView.addObject("pageInfo",pageInfo);
        return modelAndView;
    }

    @RequestMapping(value = "insertClasses")
    public ModelAndView insertClasses(ModelAndView modelAndView,Integer classesName){
        modelAndView.setViewName("redirect:selectClasses/1");
        classService.insertClasses(classesName);
        return modelAndView;
    }

    @RequestMapping(value = "deleteClasses")
    public void deleteClasses(Integer classesId){
        classService.deleteClasses(classesId);
    }

    @RequestMapping(value = "updateClasses")
    public void updateClasses( Classes classes){
        classService.updateClasses(classes);
    }

    @RequestMapping(value = "goToUpdateClasses")
    public ModelAndView goToUpdateClasses(ModelAndView modelAndView,Integer id){
        modelAndView.setViewName("updateClasses");
        modelAndView.addObject("id",id);
        return modelAndView;
    }
}
