package com.dao;

import com.pojo.Courses;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CoursesMapper extends Mapper<Courses> {
    public List<String> selectAllNames(String name);
    public List<String> selectAllNames1();

}