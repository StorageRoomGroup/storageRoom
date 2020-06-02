package com.chenxin.spring.boot.controller;

import com.chenxin.spring.boot.entity.User;
import com.chenxin.spring.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 用户列表
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<User> list() {
		List<User> list = userService.list();
		return list;
	}

}
