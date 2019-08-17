package com.service.impl;

import com.dao.CoursesMapper;
import com.dao.TeacherMapper;
import com.dao.TimeTableMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pojo.Courses;
import com.pojo.ProperTies;
import com.pojo.Teacher;
import com.pojo.TimeTable;
import com.service.TimeTableService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.annotation.Resource;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TimeTableServiceImpl implements TimeTableService {
    @Resource
    TimeTableMapper timeTableMapper;
    @Resource
    CoursesMapper coursesMapper;
    @Resource
    TeacherMapper teacherMapper;

    @Override
    public Map<String, Object> selectTimeTableByClassesId(Integer classesId, int page,Teacher teacher) {
        Map<String, Object> map = new HashMap<>();
        //先判断该班级是否有课程
        TimeTable timeTable = new TimeTable();
        timeTable.setClassesid(classesId);
        if (timeTableMapper.select(timeTable).size()!=0) {//若为空，直接跳过查询，返回空
        //先查课表表
        Example example = new Example(TimeTable.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("classesid", classesId);
        List<TimeTable> timeTableList = timeTableMapper.selectByExample(example);
        //获取课程id
        List<Integer> coursesIds = new ArrayList<>();
        for (int i = 0; i < timeTableList.size(); i++) {
            coursesIds.add(timeTableList.get(i).getCoursesid());
        }
            //通过课程id查询课程表
            Example example1 = new Example(Courses.class);
            Criteria criteria1 = example1.createCriteria();
            criteria1.andIn("coursesid", coursesIds);
            if (teacher.getTeacherid() != null) {
                criteria1.andEqualTo("teacherid", teacher.getTeacherid());
                teacher = teacherMapper.selectByPrimaryKey(teacher.getTeacherid());
            }
            PageHelper.startPage(page, Integer.parseInt(ProperTies.getPageSize()));
            List<Courses> coursesList = coursesMapper.selectByExample(example1);
            PageInfo pageInfo = new PageInfo(coursesList);
            //获取教师id
            List<Integer> teacherIds = new ArrayList<>();
            for (Courses courses : coursesList
            ) {
                teacherIds.add(courses.getTeacherid());
            }
            //通过教师id查询教师表
            List<Teacher> teachers = new ArrayList<>();
            for (int i = 0; i < teacherIds.size(); i++) {
                teachers.add(teacherMapper.selectByPrimaryKey(teacherIds.get(i)));
            }
            //查询所有教师名字
            List<Teacher> teacherNames = teacherMapper.selectAll();
            //回传查询结果
            map.put("timeTableList", timeTableList);
            map.put("coursesList", coursesList);
            map.put("pageInfo", pageInfo);
            map.put("teachers", teachers);
            map.put("teacherNames", teacherNames);
            map.put("teacher", teacher);//为啥teacher不能传入函数内更改，需要手动传出
        }
        return map;
    }

    @Override
    public void addTimeTable(Integer classesId, Integer coursesId) {
        TimeTable timeTable = new TimeTable();
        timeTable.setClassesid(classesId);
        timeTable.setCoursesid(coursesId);
        timeTableMapper.insertSelective(timeTable);
    }

    @Override
    public void deleteTimeTable(Integer classesId,Integer coursesId) {
        Example example = new Example(TimeTable.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("classesid",classesId);
        criteria.andEqualTo("coursesid",coursesId);
        timeTableMapper.deleteByExample(example);
    }
}
