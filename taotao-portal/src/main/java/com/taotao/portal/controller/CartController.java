package com.taotao.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@RequestMapping("add/{itemId}")
	public String addCartItem(@PathVariable Long itemId,@RequestParam(defaultValue="1") Integer num, HttpServletRequest request,	HttpServletResponse response){
		TaotaoResult taotaoResult = cartService.addCartItem(itemId, num, request, response);
		if(null !=taotaoResult){
			return "cartSuccess";
		}else{
			return "/error/exceprion";
		}
	}
	@RequestMapping("updateNum/{itemId}")
	public String cartUpdateNum(@PathVariable Long itemId,@RequestParam(defaultValue="1") Integer num,	HttpServletRequest request,	HttpServletResponse response){
		TaotaoResult taotaoResult =  cartService.updateCartNum(itemId, num, request, response);
		if(null !=taotaoResult){
			return "redirect:/cart/showCartItem.html";
		}else{
			return "/error/exceprion";
		}
	}
	@RequestMapping("/showCartItem")
	public String showCart(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<CartItem> list = cartService.getCartItemList(request, response);
		if(null !=list){
			model.addAttribute("cartList", list);
			return "cart";
		}else{
			return "/error/exceprion";
		}
	}
	@RequestMapping("/delete/${itemId}")
	public String deleteCartItem(@PathVariable Long itemId,HttpServletRequest request, HttpServletResponse response) {
		TaotaoResult taotaoResult = cartService.deleteCartItem(itemId, request, response);
		if(null !=taotaoResult){
			return "redirect:/cart/showCartItem.html";
		}else{
			return "/error/exceprion";
		}
	}
}
