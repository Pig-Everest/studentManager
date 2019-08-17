package com.service;

import com.pojo.Teacher;

import java.util.Map;

public interface TimeTableService {
    public Map<String,Object> selectTimeTableByClassesId(Integer classesId, int page, Teacher teacher);
    public void addTimeTable(Integer classesId,Integer coursesId);
    public void deleteTimeTable(Integer classesId,Integer coursesId);
}
