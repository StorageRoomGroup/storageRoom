package com.chenxin.spring.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
	// 访问的根目录为/admin
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String defaultPage() {
		// 后缀为.html的文件名
		return "index";
	}

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

}
