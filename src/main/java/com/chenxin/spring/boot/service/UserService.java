package com.chenxin.spring.boot.service;

import com.chenxin.spring.boot.entity.User;
import com.chenxin.spring.boot.utils.BaseResult;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by zhangtao on 2017/11/8.
 */

public interface UserService {

    BaseResult<List<User>> list();

    BaseResult add(User user);

    BaseResult login(User user, HttpSession httpSession);


}
