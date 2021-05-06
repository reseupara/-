package com.reshome.service;

import java.util.List;

import com.reshome.domain.OrderDetailListVO;
import com.reshome.domain.OrderDetailVO;
import com.reshome.domain.OrderVO;
import com.reshome.dto.Criteria;
import com.reshome.dto.OrderSaleDTO;

public interface OrderService {
	
	public void order_buy(OrderVO vo, String mbme_id) throws Exception;
								// 주문과 배송지 정보, 상품의 정보
	public void orderDirect_buy(OrderVO vo, OrderDetailVO vo2) throws Exception;
	
	// 주문리스트
	public List<OrderVO> orderInfo_list(Criteria cri) throws Exception;
	
	// 주문상품개수(페이징기능에 사용)
	public int getTotalCountOrder(Criteria cri) throws Exception;
	
	// 주문제품 상세
	public List<OrderDetailListVO> order_detail_list(long odr_code_pk) throws Exception;

	// 매출통계
	public List<OrderSaleDTO> order_sale(String startDate, String endDate) throws Exception;
	

}
