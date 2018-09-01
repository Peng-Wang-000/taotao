package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import com.taotao.service.ContentCategoryService;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;

	@Override
	public List<EUTreeNode> getContentCategoryList(Long parentId) {

		// 根据parentId查询节点列表
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		// 封装所有的节点数据
		List<EUTreeNode> resultList = new ArrayList<EUTreeNode>();

		for (TbContentCategory contentCategory : list) {
			// 创建节点
			EUTreeNode node = new EUTreeNode();
			node.setId(contentCategory.getId());
			node.setText(contentCategory.getName());
			node.setState(contentCategory.getIsParent() ? "closed" : "open");
			resultList.add(node);
		}
		return resultList;
	}

	@Override
	public TaotaoResult addContentCategory(Long parentId, String name) {
		// 新建ContentCategory记录
		TbContentCategory category = new TbContentCategory();
		category.setParentId(parentId);
		category.setName(name);
		category.setIsParent(false);
		// 状态：可选值1（正常）2（删除）
		category.setStatus(1);
		category.setSortOrder(1);
		category.setCreated(new Date());
		category.setUpdated(new Date());
		
		// 添加记录,完成后会将主键id设置到category;这里需要修改ContentCategoryMapper文件
		contentCategoryMapper.insert(category);
		//得到新建节点的父节点
		TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(parentId);
		// 查看父节点的isParent是否为true，如果是为false则改为true
		if (!parentCat.getIsParent()) {
			parentCat.setIsParent(true);
			// 更新父节点
			contentCategoryMapper.updateByPrimaryKey(parentCat);
		}

		return TaotaoResult.ok(category);
	}

	@Override
	public TaotaoResult deleteContentCategory(Long parentId, Long id) {
		Long parId = null;
		if (parentId == null) {
			TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
			parId = contentCategory.getParentId();
		} else {
			parId = parentId;
		}
		// 删除指定分类记录及其子分类记录
		contentCategoryMapper.deleteByPrimaryKey(id);
		deleteContentCatByParentId(id);
		// 根据parentId查询子节点列表，需要判断parentid对应的记录下是否有子节点。
		// 如果没有子节点，需要把parentid对应的记录的isparent改成false。
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parId);
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		if (list != null && list.size() != 0) {
			// 该父节点还存在子节点，直接返回结果
			return TaotaoResult.ok();
		} else {
			// 该父节点不存在子节点，修改isParent属性值
			TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(parId);
			parentCat.setIsParent(false);
			contentCategoryMapper.updateByPrimaryKey(parentCat);
			return TaotaoResult.ok();
		}

	}

	@Override // 根据id更新记录的name列即可
	public TaotaoResult updateContentCategory(Long id, String name) {
		TbContentCategory category = contentCategoryMapper.selectByPrimaryKey(id);
		category.setName(name);
		contentCategoryMapper.updateByPrimaryKey(category);
		return TaotaoResult.ok();
	}

	private int deleteContentCatByParentId(Long parentId) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		int effRows = contentCategoryMapper.deleteByExample(example);
		return effRows;
	}
}
