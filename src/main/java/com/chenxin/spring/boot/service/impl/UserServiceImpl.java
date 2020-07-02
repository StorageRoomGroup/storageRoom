package com.chenxin.spring.boot.service.impl;


import com.chenxin.spring.boot.entity.User;
import com.chenxin.spring.boot.entity.UserExample;
import com.chenxin.spring.boot.mapper.UserMapper;
import com.chenxin.spring.boot.service.UserService;
import com.chenxin.spring.boot.utils.BaseResult;
import com.chenxin.spring.boot.utils.Contants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by chenxin.github.com on 2017/11/8.
 */
@Service("UserService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public BaseResult<List<User>> list() {
        BaseResult<List<User>> result = new BaseResult<>();
        result.setData(userMapper.selectByExample(null));
        return result;
    }

    @Override
    public BaseResult add(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountEqualTo(user.getAccount()).andPasswordEqualTo(user.getPassword());
        List<User> users = userMapper.selectByExample(userExample);
        if (users != null && users.size() > 0) {
            return new BaseResult(201, "用户已存在");
        }
        userMapper.insert(user);
        return new BaseResult();
    }

    @Override
    public BaseResult login(User user, HttpSession httpSession) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountEqualTo(user.getAccount()).andPasswordEqualTo(user.getPassword());
        List<User> users = userMapper.selectByExample(userExample);
        if (users != null && users.size() > 0) {
            httpSession.setAttribute(Contants.USER_SESSION_TOKEN + user, user);
            return new BaseResult(200, "登录成功");
        } else {
            return new BaseResult(201, "用户不存在");
        }
    }
}
