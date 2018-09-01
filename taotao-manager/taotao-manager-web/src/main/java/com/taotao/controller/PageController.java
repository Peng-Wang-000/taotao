package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转controller
 * @author Wait
 *
 */
@Controller
public class PageController {

	/*
	 * 打开后台首页
	 */
	@RequestMapping("/")
	public String showIndex(){
		
		return "index";
	}
	/**
	 * 显示其它页面
	 * @param page
	 * @return
	 */
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page){
		
		return page;
	}
}
