package com.service.impl;

import com.dao.ClassesMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pojo.Classes;
import com.pojo.ProperTies;
import com.service.ClassService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {
    @Resource
    ClassesMapper classesMapper;

    @Override
    public PageInfo selectCLasses(int page, String classesName) {
        Example example = new Example(Classes.class);
        Criteria criteria = example.createCriteria();
        if (classesName==null){
            criteria.andCondition("classesname like ","%%");
        }else {
            criteria.andCondition("classesname like ","%"+classesName+"%");
        }
        PageHelper.startPage(page, Integer.parseInt(ProperTies.getPageSize()));
        List<Classes> list = classesMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public void insertClasses(Integer classesName) {
        Classes classes = new Classes();
        classes.setClassesname(classesName);
        classesMapper.insert(classes);
    }

    @Override
    public void deleteClasses(Integer classesId) {
        classesMapper.deleteByPrimaryKey(classesId);
    }

    @Override
    public void updateClasses(Classes classes) {
        classesMapper.updateByPrimaryKeySelective(classes);
    }

    @Override
    public Classes selectClassesById(Integer classesId) {
        return classesMapper.selectByPrimaryKey(classesId);
    }


}
