package com.taotao.search.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.search.mapper.ItemMapper;
import com.taotao.search.pojo.Item;
import com.taotao.search.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private SolrServer solrServer;

	/**
	 * 将mysql数据库中的数据提出 在solr中构建索引
	 */
	@Override
	public TaotaoResult importAllItem() {
		try {
			//获得商品列表
			List<Item> list = itemMapper.getItemList();
			Collection<SolrInputDocument> resultList = new ArrayList<>();
			//遍历商品信息，构建索引库
			for(Item item:list){
				SolrInputDocument document = new SolrInputDocument();
				document.setField("id", item.getId());
				document.setField("item_title", item.getTitle());
				document.setField("item_sell_point", item.getSell_point());
				document.setField("item_price", item.getPrice());
				document.setField("item_image", item.getImage());
				document.setField("item_desc", item.getItem_desc());
				document.setField("item_category_name", item.getCategory_name());
				resultList.add(document);
			}
			solrServer.add(resultList);
			//提交修改
			solrServer.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return TaotaoResult.ok();
	}
	
}
