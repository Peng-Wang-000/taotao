package com.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;

public interface RedisService {

	/**
	 * 同步缓冲
	 * @param contentCid
	 * @return
	 */
	public TaotaoResult syncContent(long contentCid);
}
