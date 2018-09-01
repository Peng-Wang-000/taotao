package com.taotao.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

public interface UserService {

	/**
	 * 根据参数和参数类型来校验注册的数据是否可用
	 * @param param
	 * @param type
	 * @return TaotaoResult
	 */
	public TaotaoResult checkData(String param,Integer type);
	
	/**
	 * 注册一个新的用户
	 * @param user
	 * @return TaotaoResult
	 */
	public TaotaoResult createUser(TbUser user);
	
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return TaotaoResult
	 */
	public TaotaoResult userLogin(String username, String password,HttpServletRequest request,	HttpServletResponse response);
	
	/**
	 * 根据token 查询用户信息
	 * @param token
	 * @return TaotaoResult
	 */
	public TaotaoResult getUserByToken(String token);
	
	/**
	 * 根据token来 安全退出系统
	 * @param token
	 * @return TaotaoResult
	 */
	public TaotaoResult logoutByToken(String token);
	
	
}
