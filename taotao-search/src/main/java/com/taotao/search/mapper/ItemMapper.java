package com.taotao.search.mapper;

import java.util.List;

import com.taotao.search.pojo.Item;

public interface ItemMapper {
	
	/**
	 * 使用mybatis接口绑定
	 * @return
	 */
	public List<Item> getItemList();
}
