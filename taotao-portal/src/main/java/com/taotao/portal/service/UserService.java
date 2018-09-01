package com.taotao.portal.service;

import com.taotao.pojo.TbUser;

/**
 * 管理用户信息的service
 * @author Wait
 *
 */
public interface UserService {

	/**
	 * 根据token在用户cookie中取用户信息
	 * @param token
	 * @return
	 */
	public TbUser getUserByToken(String token);
}
