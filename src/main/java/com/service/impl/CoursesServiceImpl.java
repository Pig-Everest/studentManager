package com.service.impl;

import com.dao.ClassesMapper;
import com.dao.CoursesMapper;
import com.dao.TeacherMapper;
import com.dao.TimeTableMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pojo.*;
import com.service.CoursesService;
import org.apache.ibatis.session.RowBounds;
import org.apache.logging.log4j.core.util.JsonUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CoursesServiceImpl implements CoursesService {
    @Resource
    CoursesMapper coursesMapper;
    @Resource
    TimeTableMapper timeTableMapper;
    @Resource
    ClassesMapper classesMapper;
    @Resource
    TeacherMapper teacherMapper;

    @Override
    public Map<String, Object> selectAllName(String name, Integer page) {
        Map<String, Object> map = new HashMap<>();
        List<Courses> coursesList = new ArrayList<>();
        PageHelper.startPage(page, Integer.parseInt(ProperTies.getPageSize()));
        if (name == null || name == "") {
            coursesList = coursesMapper.selectAll();
        } else {
            Example example = new Example(Courses.class);
            Criteria criteria = example.createCriteria();
            criteria.andCondition("coursesname like %"+name+"%");
            coursesList = coursesMapper.selectByExample(example);
        }
        PageInfo pageInfo = new PageInfo(coursesList);
        map.put("coursesList", coursesList);
        map.put("pageInfo", pageInfo);
        return map;
    }

    @Override
    public Map<String, Object> selectCoursesDetailByName(String name) {
        Map<String, Object> map = new HashMap<>();
        Example example = new Example(Courses.class);
        Criteria criteria = example.createCriteria();
        //先通过课程名获得课程Ids1
        criteria.andEqualTo("coursesname", name);
        List<Courses> list = coursesMapper.selectByExample(example);
        List<Integer> ids = new ArrayList<>();
        for (Courses courses : list
        ) {
            ids.add(courses.getCoursesid());
        }
        //再通过课程Ids1去课表表查询有几个班开了该课程,返回相应数量的ids
        Example example0 = new Example(TimeTable.class);
        Criteria criteria0 = example0.createCriteria();
        criteria0.andIn("coursesid",ids);
        List<TimeTable> list1 = timeTableMapper.selectByExample(example0);
        //再通过课程Ids查询课程信息
        List<Courses> coursesList = new ArrayList<>();
        for(TimeTable timeTable:list1){
            Courses courses = coursesMapper.selectByPrimaryKey(timeTable.getCoursesid());
            coursesList.add(courses);
        }
        //同时使用ids查询课表表再通过课表表查询班级表获取班级名
        Example example2 = new Example(TimeTable.class);
        Criteria criteria2 = example2.createCriteria();
        criteria2.andIn("coursesid", ids);
        List<TimeTable> timeTableList = timeTableMapper.selectByExample(example2);
        List<Classes> classesList = new ArrayList<>();
        for (TimeTable timeTable : timeTableList
        ) {
            classesList.add(classesMapper.selectByPrimaryKey(timeTable.getClassesid()));//通过班级ids查询班级名list
        }
        map.put("coursesList", coursesList);//这是课程信息List
        map.put("classesList", classesList);//这是班级信息List
        return map;
    }

    /**
     *
     * @return 班级信息和对应的教师信息
     */
    @Override
    public Map<String, Object> selectCoursesAll(Integer page) {
        Map<String, Object> map = new HashMap<>();
        //查询所有的课程信息,并获取所有教师ID
        PageHelper.startPage(page, Integer.parseInt(ProperTies.getPageSize()));
        List<Courses> coursesList = coursesMapper.selectAll();
        PageInfo pageInfo = new PageInfo(coursesList);
        List<Integer> ids = new ArrayList<>();
        for (Courses courses : coursesList
        ) {
            ids.add(courses.getTeacherid());
        }
        //同时使用ids查询教师表(每个id对应一个教师名)
        List<Teacher> teacherList = new ArrayList<>();
        for (Integer id:ids
             ) {
            teacherList.add(teacherMapper.selectByPrimaryKey(id));
        }
        map.put("coursesList", coursesList);//这是课程信息List
        map.put("teacherList", teacherList);//这是教师信息List
        map.put("pageInfo",pageInfo);
        return map;
    }

    @Override
    public void addCourses(Courses courses) {
        coursesMapper.insertSelective(courses);
    }

    @Override
    public void updateCourses(Courses courses) {
        coursesMapper.updateByPrimaryKeySelective(courses);
    }

    @Override
    public void deleteCourses(Integer coursesId) {
        coursesMapper.deleteByPrimaryKey(coursesId);
    }

    @Override
    public Map<String,Object> selectCoursesById(Integer coursesId) {
        Map<String,Object> map = new HashMap<>();
        //先查询课程信息
        Courses courses = coursesMapper.selectByPrimaryKey(coursesId);
        //再获取教师id
        Integer teacherId = courses.getTeacherid();
        //最后获取教师信息
        Teacher teacher = teacherMapper.selectByPrimaryKey(teacherId);
        map.put("courses",courses);
        map.put("teacher",teacher);
        return map;
    }


}
