package com.service.impl;

import com.dao.ClassesMapper;
import com.dao.StudentMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pojo.Classes;
import com.pojo.ProperTies;
import com.pojo.Student;
import com.service.StudentService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    StudentMapper studentMapper;
    @Resource
    ClassesMapper classesMapper;

    @Override
    public Map<String, Object> selectAllStudentByNameAndNum(String name, Integer num, Integer page) {
        Map<String, Object> map = new HashMap<>();
        //先通过学生姓名和学生学号查询
        Example example = new Example(Student.class);
        Criteria criteria = example.createCriteria();
        if (name == null || name == "") {

        } else {
            criteria.andCondition("studentname like ", "%" + name + "%");
        }
        if (num == null || num == 0) {

        } else {
            criteria.andCondition("studentnum like ", "%" + num + "%");
        }
        PageHelper.startPage(page, Integer.parseInt(ProperTies.getPageSize()));
        List<Student> studentList = studentMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(studentList);
        //再通过班级id查询班级名
        List<Classes> classesList = new ArrayList<>();
        for (Student student : studentList
        ) {
            classesList.add(classesMapper.selectByPrimaryKey(student.getClassesid()));
        }
        map.put("studentList",studentList);
        map.put("classesList",classesList);
        map.put("pageInfo",pageInfo);
        return map;
    }

    @Override
    public List<Classes> selectAllClasses() {
        return classesMapper.selectAll();
    }

    @Override
    public void insertStudent(Student student) {
        //先添加，在查询获取id手动拼接学号字符串
        studentMapper.insert(student);
        Student student1 = studentMapper.selectOne(student);
        student1.setStudentnum(student1.getClassesid()+""+student1.getStudentid());
        //再修改学号
        studentMapper.updateByPrimaryKeySelective(student1);
    }

    @Override
    public void updateStudent(Student student) {
        studentMapper.updateByPrimaryKeySelective(student);
        Student student1 = studentMapper.selectOne(student);
        student1.setStudentnum(student1.getClassesid()+""+student1.getStudentid());
        //再修改学号
        studentMapper.updateByPrimaryKeySelective(student1);
    }

    @Override
    public void deleteStudent(Integer studentId) {
        studentMapper.deleteByPrimaryKey(studentId);
    }

    @Override
    public Student selectStudentById(Integer studentId) {
        return studentMapper.selectByPrimaryKey(studentId);
    }

    @Override
    public Classes selectClassesByStudent(Student student) {
        return classesMapper.selectByPrimaryKey(student.getClassesid());
    }
}
