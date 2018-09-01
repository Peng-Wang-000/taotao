package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;
public interface ItemParamService {

	/**
	 * 通过商品类目id得到得到商品参数信息
	 * @param itemCatId
	 * @return TaotaoResult
	 */
	TaotaoResult getItemParamByCid(long itemCatId);
	/**
	 * 新增商品参数模板
	 * @param itemParam
	 * @return TaotaoResult
	 */
	TaotaoResult insertItemParam(TbItemParam itemParam);
	/**
	 * 获得商品参数模板列表
	 * @param page
	 * @param rows
	 * @return
	 */
	EUDataGridResult getItemParamList(int page,int rows);
}
