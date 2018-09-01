package com.taotao.portal.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.common.pojo.SearchResult;
import com.taotao.portal.service.SearchService;

@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	@RequestMapping("/search")
	public String searchItemList(@RequestParam(value="q")String query, @RequestParam(defaultValue="1")Integer page,Model model){
		//字符串转码
		if(query!=null)
			try {
				query = new String(query.getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		SearchResult searchResult = searchService.searchItemList(query, page);
		//报空指针异常
		if(null==searchResult.getObjectList()){
			return "search";
		}else{
			model.addAttribute("query", query);
			model.addAttribute("itemList", searchResult.getObjectList());
			model.addAttribute("totalPages", searchResult.getPageCount());
			model.addAttribute("page", searchResult.getCurPge());
			return "search";
		}
	}
}
