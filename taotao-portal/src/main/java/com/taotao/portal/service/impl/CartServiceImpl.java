package com.taotao.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbItem;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${ITME_INFO_URL}")
	private String ITME_INFO_URL;
	@Value("${CART_COOKIE_NAME}")
	private String CART_COOKIE_NAME;

	@Override
	public TaotaoResult addCartItem(long itemId, int num, HttpServletRequest request, HttpServletResponse response) {

		// 构建一个购物车中的商品信息
		CartItem cartItem = null;
		// 从Cookie中取出商品信息列表
		List<CartItem> itemList = getCartItemList(request);
		if (itemList.size() != 0) {
			for (CartItem cItem : itemList) {
				if (cItem.getId() == itemId) {
					cItem.setNum(cItem.getNum() + num);
					cartItem = cItem;
					break;
				}
			}
		}
		// 购物车中不存在该商品
		if (cartItem == null) {
			cartItem = new CartItem();
			// 调用rest服务通过商品id来查询商品信息
			String json = HttpClientUtil.doGet(REST_BASE_URL + ITME_INFO_URL + itemId);
			// 将json数据转java对象
			TaotaoResult result = TaotaoResult.formatToPojo(json, TbItem.class);
			if (result.getStatus() == 200) {
				TbItem item = (TbItem) result.getData();
				cartItem.setId(item.getId());
				cartItem.setImage(item.getImage() == null ? "" : item.getImage().split(",")[0]);
				cartItem.setNum(num);
				cartItem.setPrice(item.getPrice());
				cartItem.setTitle(item.getTitle());
			}
			// 添加到购物车列表
			itemList.add(cartItem);
		}
		// 把购物车信息重新写入cookie中
		CookieUtils.setCookie(request, response, CART_COOKIE_NAME, JsonUtils.objectToJson(itemList), true);
		return TaotaoResult.ok();
	}

	@Override
	public List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response) {
		List<CartItem> itemlist = getCartItemList(request);
		return itemlist;
	}

	@Override
	public TaotaoResult updateCartNum(Long itemId, Integer num, HttpServletRequest request,
			HttpServletResponse response) {
		// 构建一个购物车中的商品信息
		CartItem cartItem = null;
		// 从Cookie中取出商品信息列表,存在该商品
		List<CartItem> itemList = getCartItemList(request);
		for (CartItem cItem : itemList) {
			if (cItem.getId() == itemId) {
				//商品数量最少为1个
				if(num<1)
					cItem.setNum(1);
				else
					cItem.setNum(num);
				cartItem = cItem;
				break;
			}
		}
		// 把购物车信息重新写入cookie中
		CookieUtils.setCookie(request, response, CART_COOKIE_NAME, JsonUtils.objectToJson(itemList), true);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response) {
		// 从cookie中取购物车商品列表
		List<CartItem> itemList = getCartItemList(request);
		// 从列表中找到此商品
		for (CartItem cartItem : itemList) {
			itemList.remove(cartItem);
			break;
		}
		// 把购物车列表重新写入cookie
		CookieUtils.setCookie(request, response, CART_COOKIE_NAME, JsonUtils.objectToJson(itemList), true);

		return TaotaoResult.ok();
	}

	private List<CartItem> getCartItemList(HttpServletRequest request) {
		// 从cookie中取商品列表
		String cartJson = CookieUtils.getCookieValue(request, CART_COOKIE_NAME,true);
		if (null == cartJson) {
			return new ArrayList<CartItem>();
		}
		try {
			// 把json转换成商品列表
			List<CartItem> list = JsonUtils.jsonToList(cartJson, CartItem.class);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<CartItem>();
	}

}
