package com.service;

import com.pojo.Teacher;

import java.util.List;
import java.util.Map;

public interface TeacherService {
    public Map<String,Object> selectTeacherAll(int page,String name);
    /*public Map<String,Object> selectTeacherDetailById(int id);*///淘汰的方法
    //通过教师id和班级名获取满足要求的课程id
    public Map<String,Object> selectTeacherDetailByName(Integer teacherId,Integer classesId);
    public List<String> selectCourseNameByTeacherId(Integer teacherId);
    public List<Teacher> selectTeacherAllNoPage();
    public void insertTeacher(Teacher teacher);
    public void updateTeacher(Teacher teacher);
    public void deleteTeacher(Integer teacherId);
    public Teacher selectTeacherById(Integer teacherId);
    public Map<String,Object> selectCoursesByCoursesName(String coursesName,List<Integer> coursesIds);
    public void updateTeacherDetailById(List<Integer> ids,String name);
    public void deleteTeacherDetailByIds(List<Integer> ids);
}
