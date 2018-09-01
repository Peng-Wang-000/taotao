package com.taotao.search.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.taotao.common.pojo.SearchResult;
import com.taotao.search.dao.SearchDao;
import com.taotao.search.pojo.Item;

@Repository
public class SearchDaoImpl implements SearchDao {

	@Autowired
	private SolrServer solrServer;

	@Override
	public SearchResult search(SolrQuery query) throws Exception {
		// 新建查询结果
		SearchResult searchResult = new SearchResult();
		// 获得查询response
		QueryResponse queryResponse = solrServer.query(query);
		// 取高亮显示
		Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();

		// 获得查询数据
		SolrDocumentList solrDocumentList = queryResponse.getResults();
		// 获得查询总记录数
		long recordCount = solrDocumentList.getNumFound();
		// 商品列表
		List<Item> itemList = new ArrayList<Item>();

		// 遍历查询数据，构建Item
		for (SolrDocument solrDocument : solrDocumentList) {
			String title = "";

			// 取高亮显示的结果
			List<String> titleList = highlighting.get(solrDocument.get("id")).get("item_title");
			if (titleList != null && titleList.size() > 0)
				title = titleList.get(0);
			else
				title = (String) solrDocument.get("item_title");

			Item item = new Item();
			item.setId((String) solrDocument.get("id"));
			item.setTitle(title);
			item.setCategory_name((String) solrDocument.get("item_category_name"));
			item.setImage((String) solrDocument.get("item_image"));
			// item.setItem_desc((String) solrDocument.get("item_desc"));
			item.setPrice((Long) solrDocument.get("item_price"));
			item.setSell_point((String) solrDocument.get("item_sell_point"));
			
			itemList.add(item);
		}
		searchResult.setObjectList(itemList);
		searchResult.setRecordCount(recordCount);
		return searchResult;
	}

}
