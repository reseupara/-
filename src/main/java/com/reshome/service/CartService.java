package com.reshome.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.json.simple.JSONObject;

import com.reshome.domain.CartVO;
import com.reshome.domain.CartVOList;
import com.reshome.dto.CartDTO;

public interface CartService {
	
	public void add_cart(CartVO vo) throws Exception;
	
	public List<CartVOList> list_cart(String mbme_id) throws Exception;
	
	public void cart_delete(long cart_code_pk) throws Exception;
	
	public void cartAll_delete(String mbme_id) throws Exception;

	public void cart_amount_update(@Param("cart_code_pk") int cart_code_pk, @Param("cart_amount") int cart_amount) throws Exception;

	public void cart_check_delete(List<Integer> checkArr) throws Exception;
	
	public List<CartDTO> cart_money() throws Exception;
	
	public JSONObject getChartData();
	
	public void cart_not_check_delete(List<Integer> checkArr) throws Exception;
}
