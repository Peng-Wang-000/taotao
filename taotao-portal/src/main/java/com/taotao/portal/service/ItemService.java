package com.taotao.portal.service;

import com.taotao.portal.pojo.ItemInfo;

public interface ItemService {

	/**
	 * 根据商品Id获得商品基本信息
	 * @param itemId
	 * @return ItemInfo
	 */
	public ItemInfo getItemById(Long itemId);
	
	/**
	 * 根据商品Id获得商品描述信息
	 * @param itemId
	 * @return html片段
	 */
	public String getItemDescById(Long itemId);
	
	/**
	 * 根据商品Id获得商品参数信息
	 * @param itemId
	 * @return html片段
	 */
	public String getItemParam(Long itemId);
}
