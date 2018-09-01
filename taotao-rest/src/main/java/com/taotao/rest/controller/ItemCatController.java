package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;

@RequestMapping("/itemcat")
@Controller
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;

/*	法一：解决json字符串乱码问题
 * @RequestMapping(value = "/itemcat/all", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public String getItemCatList(String callback) {
		CatResult catResult = itemCatService.getItemCatList();
		// 把pojo装换成json字符串s
		String json = JsonUtils.objectToJson(catResult);
		// 将json数据封装成js脚本数据，解决跨域问题
		String result = callback + "(" + json + ")";
		return result;
	}*/
	@RequestMapping("/all")//作为服务端只需要定义好访问的url即可
	@ResponseBody
	public Object getItemCatList(String callback) {
		CatResult catResult = itemCatService.getItemCatList();
		//spring4.1以后采用该方法解决乱码问题,jsonp跨域请求
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(catResult);
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}
}
