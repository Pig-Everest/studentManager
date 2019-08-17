package com.dao;

import com.pojo.Teacher;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TeacherMapper extends Mapper<Teacher> {
    public List<Integer> selectDetailCoursesId(@Param("teacherid") Integer teacherid,@Param("classesid") Integer classesid);
    public List<Integer> selectDetailCoursesId1(Integer teacherId);
    public List<String> selectCourseNameByTeacherId(Integer teacherid);
}