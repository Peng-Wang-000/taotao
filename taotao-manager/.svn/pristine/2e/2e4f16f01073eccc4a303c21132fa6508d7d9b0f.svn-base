package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemParamService;

@RequestMapping("/item/param")
@Controller
public class ItemParamController {

	@Autowired
	private ItemParamService itemParamService;
	
	@RequestMapping("/query/itemcatid/{itemCatId}")
	@ResponseBody
	public TaotaoResult getItemParamByCid(@PathVariable Long itemCatId){
		TaotaoResult result = itemParamService.getItemParamByCid(itemCatId);
		return result;
	}
	
	/**
	 * 新增商品参数模板控制器
	 * @param itemCatId
	 * @param paramData
	 * @return
	 */
	@RequestMapping("/save/{itemCatId}")
	@ResponseBody
	public TaotaoResult insertItemParam(@PathVariable Long itemCatId,String paramData){
		
		TbItemParam itemParam = new TbItemParam();
		
		itemParam.setItemCatId(itemCatId);
		itemParam.setParamData(paramData);
		TaotaoResult result = itemParamService.insertItemParam(itemParam );
		return result;
	}
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getItemParamList(@RequestParam(defaultValue="1")int page,@RequestParam(defaultValue="30")int rows)throws Exception{
		EUDataGridResult result = itemParamService.getItemParamList(page, rows);
		return result;
	}
	
	
}
