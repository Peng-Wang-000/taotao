package com.taotao.portal.pojo;

import com.taotao.pojo.TbItem;

/**
 * 为了解决item存在多图片的问题，采用继承关系获得images属性
 * @author Wait
 *
 */
public class ItemInfo extends TbItem {

	public String[] getImages() {
		String image = getImage();
		if(image!=null){
			String[] images = image.split(",");
			return images;
		}
		return null;
	}
}
