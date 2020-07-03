package com.chenxin.spring.boot.service.impl;


import com.chenxin.spring.boot.entity.User;
import com.chenxin.spring.boot.entity.UserExample;
import com.chenxin.spring.boot.exception.BusinessException;
import com.chenxin.spring.boot.mapper.UserMapper;
import com.chenxin.spring.boot.service.UserService;
import com.chenxin.spring.boot.utils.BaseResult;
import com.chenxin.spring.boot.utils.Contants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by chenxin.github.com on 2017/11/8.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public BaseResult<List<User>> list(User user) {
        BaseResult<List<User>> result = new BaseResult<>();
        UserExample userExample = new UserExample();
        if (!StringUtils.isEmpty(user.getAccount())) {
            userExample.createCriteria().andAccountLike(user.getAccount());
        }
        result.setData(userMapper.selectByExample(userExample));
        return result;
    }

    @Override
    public BaseResult add(User user) {
        user.setGmtCreate(new Date());
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountEqualTo(user.getAccount());
        List<User> users = userMapper.selectByExample(userExample);
        if (users != null && users.size() > 0) {
            throw BusinessException.error(201, "用户已存在");
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
            String token = Contants.USER_SESSION_TOKEN + user.getAccount() + users.get(0).getId();
            httpSession.setAttribute(token, user);
            return new BaseResult<String>(200, "登录成功", token);
        } else {
            throw BusinessException.error(201, "用户不存在");
        }
    }
}
