package com.service;

import com.github.pagehelper.PageInfo;
import com.pojo.Classes;

import java.util.List;

public interface ClassService {
    public PageInfo selectCLasses(int page, String classesName);
    public void insertClasses(Integer classesName);
    public void deleteClasses(Integer classesId);
    public void updateClasses(Classes classes);
    public Classes selectClassesById(Integer classesId);
}
