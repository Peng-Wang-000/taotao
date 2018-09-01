package com.taotao.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {

	/**
	 * 展示注册页面
	 * @return
	 */
	@RequestMapping("/showRegister")
	public String showRegister(){
		return "register";
	}
	/**
	 * 展示登录
	 * @return
	 */
	@RequestMapping("/showLogin")
	public String showLogin(String redirect,Model model){
		/*回调url应该是通过一个参数传递给显示登录页面的Controller。
		 * 参数名为：redirect 需要把回调的url传递给jsp页面。
		 * 当登录成功后，js的逻辑中判断是否有回调的rul，如果有就跳转到此url，如果没有就跳转到商城首页。*/
		model.addAttribute("redirect", redirect);
		return "login";
	}
}
