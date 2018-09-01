package com.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;

public interface ItemService {

	/**
	 * 获取商品基本信息
	 * @param itemId
	 * @return TaotaoResult
	 */
	TaotaoResult getItemBaseInfo(long itemId);
	
	/**
	 * 获取商品描述信息
	 * @param itemId
	 * @return TaotaoResult
	 */
	
	public TaotaoResult getItemDesc(long itemId);
	/**
	 * 接收商品id调用mapper查询商品规格参数，
	 * 返回规格参数pojo使用TaotaoResult包装,添加缓存逻辑。
	 * @param itemId
	 * @return TaotaoResult
	 */
	public TaotaoResult getItemParam(long itemId);
}
