package com.chenxin.spring.boot.service.impl;


import com.chenxin.spring.boot.entity.User;
import com.chenxin.spring.boot.entity.UserExample;
import com.chenxin.spring.boot.mapper.UserMapper;
import com.chenxin.spring.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by chenxin.github.com on 2017/11/8.
 */
@Service("UserService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> list() {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andIsDeletedEqualTo(false);
        return userMapper.selectByExample(userExample);
    }
}
