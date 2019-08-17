package com.service.impl;

import com.dao.User1Mapper;
import com.pojo.User1;
import com.service.UserService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    User1Mapper user1Mapper;

    @Override
    public Boolean checkUserLogin(User1 user1) {
        Boolean flag = false;
        Example example = new Example(User1.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("useraccount",user1.getUseraccount())
                .andEqualTo("userpwd",user1.getUserpwd());
        if (user1Mapper.selectByExample(example).size()!=0){
            flag = true;
        }
        return flag;
    }
}
