package com.taotao.portal.service;

import com.taotao.common.pojo.SearchResult;

/**
 * 使用远程solr服务进行搜索操作
 * @author Wait
 *
 */
public interface SearchService {

	/**
	 * 使用HttpClientUtil工具类调用搜索服务，返回一个json数据.
	 * @param query
	 * @param page
	 * @return
	 */
	public SearchResult searchItemList(String query ,long page);
	
}
