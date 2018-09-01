package com.taotao.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.pojo.CartItem;

public interface CartService {

	/**
	 * 添加购物车商品
	 * @param itemId
	 * @param num
	 * @param request
	 * @param response
	 * @return
	 */
	public TaotaoResult addCartItem(long itemId, int num, 
			HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * 把Cookie中的商品列表取出来
	 * @param request
	 * @param response
	 * @return
	 */
	public List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response);

	/**
	 * 修改购物车中某商品的数量
	 * @param itemId
	 * @param num
	 * @param request
	 * @param response
	 * @return
	 */
	public TaotaoResult updateCartNum(Long itemId, Integer num, HttpServletRequest request,
			HttpServletResponse response);

	/**
	 * 删除购物车中的指定商品
	 * @param itemId
	 * @param request
	 * @param response
	 * @return TaotaoResult
	 */
	public TaotaoResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response);

}
