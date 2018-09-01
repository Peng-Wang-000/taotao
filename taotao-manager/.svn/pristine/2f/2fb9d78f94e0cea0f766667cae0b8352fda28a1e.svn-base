package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {

	/**
	 * 实现获得所有的Content数据并分页
	 * @param categoryId
	 * @param page
	 * @param rows
	 * @return EUDataGridResult
	 */
	public EUDataGridResult getContentList(Long categoryId, Integer page, Integer rows);

	/**
	 * 接收表tb_content对应的pojo对象。把pojo对象插入到tb_content表中。
	 * @param content
	 * @return TaotaoResult
	 * @throws Exception
	 */
	public TaotaoResult addContent(TbContent content) throws Exception;

	/**
	 * 删除指定id的Content
	 * @param ids
	 * @return
	 */
	public TaotaoResult deleteContentByIds(Long[] ids);

	/**
	 * 更新数据
	 * @param content
	 * @return
	 */
	public TaotaoResult updateContent(TbContent content);
}
