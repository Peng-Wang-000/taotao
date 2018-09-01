package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.service.ItemCatService;

/**
 * 商品分类管理service
 * @author Wait
 *
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;

	/**
	 * 根据parentId获得所有子节点列表
	 */
	@Override
	public List<TbItemCat> getItemCatList(Long parentId) {
		// 设置查询条件
		TbItemCatExample itemCatExample = new TbItemCatExample();
		Criteria criteria = itemCatExample.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		
		// 根据parentid查询子节点
		List<TbItemCat> itemCatlist = itemCatMapper.selectByExample(itemCatExample);
		if (itemCatlist != null && itemCatlist.size() > 0) {
			// 返回子节点列表
			return itemCatlist;
		}
		return null;
	}
}
