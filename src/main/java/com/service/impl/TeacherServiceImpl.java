package com.service.impl;

import com.dao.ClassesMapper;
import com.dao.CoursesMapper;
import com.dao.TeacherMapper;
import com.dao.TimeTableMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pojo.*;
import com.service.TeacherService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.annotation.Resource;
import java.util.*;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Resource
    TeacherMapper teacherMapper;
    @Resource
    ClassesMapper classesMapper;
    @Resource
    CoursesMapper coursesMapper;
    @Resource
    TimeTableMapper timeTableMapper;

    @Override
    public Map<String, Object> selectTeacherAll(int page, String name) {
        Map<String, Object> map = new HashMap<>();
        Example example = new Example(Teacher.class);
        Criteria criteria = example.createCriteria();
        if (name == null) {
            criteria.andCondition("teachername like ", "%%");
        } else {
            criteria.andCondition("teachername like ", "%" + name + "%");
        }
        PageHelper.startPage(page, Integer.parseInt(ProperTies.getPageSize()));
        List<Teacher> teacherList = teacherMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(teacherList);
        map.put("pageInfo", pageInfo);
        map.put("teacherList", teacherList);
        return map;
    }

    // 淘汰的方法(我好心痛啊)
    /*@Override
    public Map<String, Object> selectTeacherDetailById(int id) {
        Map<String, Object> map = new HashMap<>();
        //先通过teacherId查询课程表
        Example example = new Example(Teacher.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teacherid", id);
        List<Courses> coursesList = coursesMapper.selectByExample(example);
        List<Integer> coursesIds = new ArrayList<>();
        for (Courses courses : coursesList
        ) {
            coursesIds.add(courses.getCoursesid());
        }
        //再通过coursesid 查询课表表
        Example example1 = new Example(TimeTable.class);
        Criteria criteria1 = example1.createCriteria();
        criteria1.andIn("couresesid", coursesIds);
        List<TimeTable> timeTableList = timeTableMapper.selectByExample(example1);
        //再通过classesids查询班级表
        List<Classes> classesList = new ArrayList<>();
        for (TimeTable timeTable : timeTableList
        ) {
            classesList.add(classesMapper.selectByPrimaryKey(timeTable.getClassesid()));
        }
        map.put("coursesList",coursesList);//这是课程list
        map.put("classesList",classesList);//这是班级list
        return map;
    }*/

    /**
     * 通过教师id和班级名获取满足要求的课程id
     *
     * @param teacherId 教师id
     * @param classesId
     * @return
     */
    @Override
    public Map<String, Object> selectTeacherDetailByName(Integer teacherId, Integer classesId) {
        Map<String, Object> map = new HashMap<>();
        //通过教师id和班级名获取满足要求的课程id
        List<Integer> coursesIds = new ArrayList<>();
        if (classesId == null || classesId == 0) {
            coursesIds = teacherMapper.selectDetailCoursesId1(teacherId);
        } else {
            coursesIds = teacherMapper.selectDetailCoursesId(teacherId, classesId);
        }
        //判断ids是否为空，为空则跳过其余步骤
        if (coursesIds.size() != 0) {
            //再通过课程id查询课表表获取该课程所开的班级数量
            Example example0 = new Example(TimeTable.class);
            Criteria criteria0 = example0.createCriteria();
            criteria0.andIn("coursesid",coursesIds);
            List<TimeTable> list1 = timeTableMapper.selectByExample(example0);
            //再通过课程Ids查询课程信息
            List<Courses> coursesList = new ArrayList<>();
            for(TimeTable timeTable:list1){
                Courses courses = coursesMapper.selectByPrimaryKey(timeTable.getCoursesid());
                coursesList.add(courses);
            }
            //再通过课程id查询课表表
            Example example1 = new Example(TimeTable.class);
            Criteria criteria1 = example1.createCriteria();
            criteria1.andIn("coursesid", coursesIds);
            List<TimeTable> timeTableList = timeTableMapper.selectByExample(example1);
            //再通过classesids查询班级表
            List<Classes> classesList = new ArrayList<>();
            for (TimeTable timeTable : timeTableList
            ) {
                classesList.add(classesMapper.selectByPrimaryKey(timeTable.getClassesid()));
            }
            //单独查询当前id
            Classes classes = classesMapper.selectByPrimaryKey(classesId);
            map.put("coursesList", coursesList);//这是课程list
            map.put("classesList", classesList);//这是班级list
            map.put("classes", classes);//这是当前班级信息
        }
        return map;
    }

    @Override
    public List<String> selectCourseNameByTeacherId(Integer teacherId) {
        List<String> list = teacherMapper.selectCourseNameByTeacherId(teacherId);
        return list;
    }

    @Override
    public List<Teacher> selectTeacherAllNoPage() {
        List<Teacher> list = teacherMapper.selectAll();
        return list;
    }

    @Override
    public void insertTeacher(Teacher teacher) {
        teacherMapper.insertSelective(teacher);
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        teacherMapper.updateByPrimaryKeySelective(teacher);
    }

    @Override
    public void deleteTeacher(Integer teacherId) {
        teacherMapper.deleteByPrimaryKey(teacherId);
    }

    @Override
    public Teacher selectTeacherById(Integer teacherId) {
        return teacherMapper.selectByPrimaryKey(teacherId);
    }

    @Override
    public Map<String, Object> selectCoursesByCoursesName(String coursesName, List<Integer> coursesIds) {
        Map<String, Object> map = new HashMap<>();
        //先从前两个参数获取相关的课程信息
        Example example = new Example(Courses.class);
        Criteria criteria = example.createCriteria();
        criteria.andIn("coursesid", coursesIds);
        criteria.andEqualTo("coursesname", coursesName);
        List<Courses> coursesList = coursesMapper.selectByExample(example);
        String name = coursesList.get(0).getCoursesname();
        map.put("coursesList", coursesList);
        map.put("name", name);
        return map;
    }

    @Override
    public void updateTeacherDetailById(List<Integer> ids, String name) {
        for (Integer id : ids
        ) {
            Courses courses = new Courses();
            courses.setCoursesid(id);
            courses.setCoursesname(name);
            coursesMapper.updateByPrimaryKeySelective(courses);
        }
    }

    @Override
    public void deleteTeacherDetailByIds(List<Integer> ids) {
        Example example = new Example(Courses.class);
        Criteria criteria = example.createCriteria();
        criteria.andIn("coursesid",ids);
        coursesMapper.deleteByExample(example);
    }
}
