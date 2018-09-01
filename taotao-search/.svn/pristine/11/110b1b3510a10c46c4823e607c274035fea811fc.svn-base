package com.taotao.search.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.SearchResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.search.service.SearchService;

@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;

	@RequestMapping(value = "/queryItem", method = RequestMethod.GET)
	@ResponseBody
	public TaotaoResult searchItem(@RequestParam(value = "q") String query, @RequestParam(defaultValue = "1") long page,
			@RequestParam(defaultValue = "30") long rows) {
		// 查询条件不能为空
		if (StringUtils.isBlank(query)) {
			return TaotaoResult.build(400, "查询条件不能为空!");
		}
		SearchResult searchResult = null;
		try {
			// 处理get乱码
			query = new String(query.getBytes("ISO8859-1"), "utf-8");
			searchResult = searchService.searcherItem(query, page, rows);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return TaotaoResult.ok(searchResult);
	}
}
