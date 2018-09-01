package com.taotao.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;
	@Autowired
	private JedisClient jedisClient;

	@Value("${INDEX_CONTENT_CATEGORY_REDIS_KEY}")
	private String INDEX_CONTENT_CATEGORY_REDIS_KEY;

	@Override
	public CatResult getItemCatList() {
		// 从缓存中取内容
		try {
			String result = jedisClient.hget(INDEX_CONTENT_CATEGORY_REDIS_KEY, "itemCatList");
			if (!StringUtils.isBlank(result)) {
				// 将字符串数据变成list
				List<CatNode> resultList = JsonUtils.jsonToList(result, CatNode.class);
				CatResult catResult = new CatResult();
				catResult.setData(resultList);
				return catResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		CatResult catResult = new CatResult();
		List<TbItemCat> catList = (List<TbItemCat>) getCatList(0);
		// 查询分类列表
		catResult.setData(catList);
		
		// 在缓存中存内容
		try {
			//将list变为字符串
			String cacheString = JsonUtils.objectToJson(catList);
			jedisClient.hset(INDEX_CONTENT_CATEGORY_REDIS_KEY, "itemCatList", cacheString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return catResult;
	}

	/**
	 * 查询分类列表
	 * 
	 * @param parentId
	 * @return
	 */
	private List<?> getCatList(long parentId) {
		// 创建查询条件
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		// 执行查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		// 返回值list
		List<Object> resultList = new ArrayList<Object>();
		int count = 0;
		// 向list中添加节点
		for (TbItemCat tbItemCat : list) {
			// 判断是否为父节点
			if (tbItemCat.getIsParent()) {
				CatNode catNode = new CatNode();
				if (parentId == 0) {
					catNode.setName("<a href='/products/" + tbItemCat.getId() + ".html'>" + tbItemCat.getName() + "</a>");
				} else {
					catNode.setName(tbItemCat.getName());
				}
				catNode.setUrl("/products/" + tbItemCat.getId() + ".html");

				// 递归操作
				catNode.setItem(getCatList(tbItemCat.getId()));

				resultList.add(catNode);

				count++;
				if (count >= 14) {
					break;
				}
				// 如果是叶子节点
			} else {
				resultList.add("/products/" + tbItemCat.getId() + "|" + tbItemCat.getName());
			}
		}
		return resultList;
	}
}
