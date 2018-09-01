package com.taotao.portal.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.SearchResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.portal.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {

	@Value("${SEARCH_BASE_URL}")
	private String SEARCH_BASE_URL;
	
	@Override
	public SearchResult searchItemList(String query, long page){
		//设置查询参数
		Map<String, String> param = new HashMap<>();
		param.put("q", query);
		param.put("page",page+"");
		try {
			//调用taotao-search提供的搜索服务http://127.0.0.1:8083/search/queryItem/queryItem
			String json = HttpClientUtil.doGet(SEARCH_BASE_URL+"queryItem", param);
			//转换成taotaoResult对象
			TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, SearchResult.class);
			//查询成功
			if (taotaoResult.getStatus() == 200) {
				//取查询结果
				SearchResult searchResult = (SearchResult) taotaoResult.getData();
				return searchResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
