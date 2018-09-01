package com.taotao.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.pojo.TbItemCat;
import com.taotao.service.ItemCatService;

/**
 * 商品类目控制器
 * 
 * @author Wait
 *
 */
@RequestMapping("/item/cat")
@Controller
public class ItemCatController {

	@Autowired
	private ItemCatService itemcatService;

	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNode> categoryList(@RequestParam(value = "id", defaultValue = "0") Long parentId) throws Exception {

		List<EUTreeNode> catList = new ArrayList<EUTreeNode>();

		// 获得商品类目列表
		List<TbItemCat> nodes = itemcatService.getItemCatList(parentId);

		// 遍历TbItemCat
		for (TbItemCat itemCat : nodes) {
			/*
			 * 采用Map操作 Map<String,Object> node = new HashMap<String,Object>();
			 * node.put("id", tbItemCat.getId()); node.put("text",
			 * tbItemCat.getName()); 如果是父节点的话就设置成关闭状态，如果是叶子节点就是
			 * open状态node.put("state", tbItemCat.getIsParent()?"closed":"open");
			 */
			//构建pojo来表示Easy树形控件节点
			EUTreeNode node = new EUTreeNode();
			node.setId(itemCat.getId());
			node.setText(itemCat.getName());
			node.setState(itemCat.getIsParent()?"closed":"open");
			
			catList.add(node);
		}
		//返回结果集，且以json数据返回
		return catList;
	}

}
