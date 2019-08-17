package com.service;

import com.pojo.Classes;
import com.pojo.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {
    public Map<String,Object> selectAllStudentByNameAndNum(String name,Integer num,Integer page);
    public List<Classes> selectAllClasses();
    public void insertStudent(Student student);
    public void updateStudent(Student student);
    public void deleteStudent(Integer studentId);
    public Student selectStudentById(Integer studentId);
    public Classes selectClassesByStudent(Student student);
}
