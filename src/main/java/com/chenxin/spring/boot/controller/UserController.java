package com.chenxin.spring.boot.controller;

import com.chenxin.spring.boot.entity.User;
import com.chenxin.spring.boot.service.UserService;
import com.chenxin.spring.boot.utils.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户列表
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public BaseResult<List<User>> list(@RequestBody User user) {
        return userService.list(user);
    }

    /**
     * 用户新增
     *
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseResult add(@RequestBody User user) {
        return userService.add(user);
    }

    /**
     * 用户登录
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseResult login(@RequestBody User user, HttpSession httpSession) {
        return userService.login(user, httpSession);
    }


}
