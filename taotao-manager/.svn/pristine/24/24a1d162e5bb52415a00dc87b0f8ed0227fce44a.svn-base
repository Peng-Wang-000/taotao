package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ContentCategoryService;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

	@Autowired
	private ContentCategoryService contentCategoryService;
	
	@ResponseBody
	@RequestMapping("/list")
	public List<EUTreeNode> getContentCategoryList(@RequestParam(value="id",defaultValue="0")Long parentId){
		//获得EasyUI异步Tree所有节点
		List<EUTreeNode> result = contentCategoryService.getContentCategoryList(parentId);
		return result;
	}
	@ResponseBody
	@RequestMapping("/create")
	public TaotaoResult createContentCategory(Long parentId,String name){
		TaotaoResult result = contentCategoryService.addContentCategory(parentId,name);
		return result;
	}
	@ResponseBody
	@RequestMapping("/delete")
	public TaotaoResult deleteContentCategory(Long parentId,Long id){
		TaotaoResult result = contentCategoryService.deleteContentCategory(parentId,id);
		return result;
	}
	@ResponseBody
	@RequestMapping("/update")
	public TaotaoResult reNameContentCategory(Long id,String name){
		TaotaoResult result = contentCategoryService.updateContentCategory(id,name);
		return result;
	}
}
