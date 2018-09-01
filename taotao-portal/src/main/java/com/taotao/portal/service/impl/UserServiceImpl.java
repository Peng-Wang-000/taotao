package com.taotao.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.pojo.TbUser;
import com.taotao.portal.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Value("${SSO_BASE_URL}")
	public String SSO_BASE_URL;
	@Value("${SSO_GET_USER_BY_TOKEN_URL}")
	private String SSO_GET_USER_BY_TOKEN_URL;
	@Value("${SSO_PAGE_LOGIN}")
	public String SSO_PAGE_LOGIN;
	
	@Override
	public TbUser getUserByToken(String token) {
		try {
			//根据用户提供的token从单点登录系统获得用户信息
			String json = HttpClientUtil.doGet(SSO_BASE_URL+SSO_GET_USER_BY_TOKEN_URL+token);
			//将json数据转成java对象
			TaotaoResult result = TaotaoResult.formatToPojo(json, TbUser.class);
			if(result.getStatus()==200){
				return (TbUser) result.getData();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
