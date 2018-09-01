package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {

	/**
	 * 根据商品ID查询商品
	 * @param itemId
	 * @return TbItem
	 */
	public TbItem getItemById(long itemId);
	/**
	 * 获得分页数据
	 * @param page
	 * @param rows
	 * @return EUDataGridResult
	 */
	public EUDataGridResult getItemList(int page, int rows);
	
	/**
	 * 添加商品,同时添加商品描述
	 * @param item
	 * @param desc
	 * @param itemParams
	 * @return TaotaoResult
	 * @throws Exception
	 */

	public TaotaoResult createItem(TbItem item,String desc,String itemParams)throws Exception;
}
