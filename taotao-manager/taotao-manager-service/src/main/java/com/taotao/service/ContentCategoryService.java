package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;

public interface ContentCategoryService {

	/**
	 * 根据parentId，从tb_content_category表中获得所有的子节点（使用EUTreeNode表示节点）
	 * @param parentId
	 * @return List<EUTreeNode>
	 */
	public List<EUTreeNode> getContentCategoryList(Long parentId);
	/**
	 * 向tb_content_category表中添加一条记录。返回TaoTaoResult包含记录的pojo对象
	 * @param parentId 父节点id
	 * @param name 当前节点的名称
	 * @return TaotaoResult
	 */
	public TaotaoResult addContentCategory(Long parentId,String name);
	/**
	 * 删除tb_content_category表中指定id的记录。返回TaoTaoResult包含记录的pojo对象
	 * @param parentId 父节点id
	 * @param id 需要删除的id
	 * @return TaotaoResult
	 */
	public TaotaoResult deleteContentCategory(Long parentId,Long id);
	/**
	 * 修改指定记录属性值
	 * @param id
	 * @param name
	 * @return
	 */
	public TaotaoResult updateContentCategory(Long id, String name);
}
