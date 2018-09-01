package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;

//内容控制器
@RequestMapping("/content")
@Controller
public class ContentController {

	@Autowired
	private ContentService contentService;

	@RequestMapping("/query/list")
	@ResponseBody
	public EUDataGridResult getContentList(Long categoryId, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "20") Integer rows) {

		EUDataGridResult result = contentService.getContentList(categoryId, page, rows);
		return result;
	}
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult createContent(TbContent content) throws Exception {
		TaotaoResult result = contentService.addContent(content);
		return result;
	}
	@RequestMapping("/edit")
	@ResponseBody
	public TaotaoResult editContent(TbContent content) throws Exception {
		TaotaoResult result = contentService.updateContent(content);
		return result;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public TaotaoResult deleteContent(Long[] ids) {
		TaotaoResult result = contentService.deleteContentByIds(ids);
		return result;
	}

}
