package com.reshome.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.reshome.domain.OrderDetailListVO;
import com.reshome.domain.OrderDetailVO;
import com.reshome.domain.OrderVO;
import com.reshome.dto.Criteria;
import com.reshome.dto.OrderSaleDTO;

public interface OrderMapper {
	
	// 주문정보
	public void order_add(OrderVO vo) throws Exception;
	
	// 주문상세정보(주문내역)
	public void orderDetail_add(@Param("odr_code_pk") long odr_code_pk,@Param("mbme_id") String mbme_id) throws Exception;
	
	public void orderDirect_add(OrderDetailVO vo) throws Exception;
	
	// 주문리스트
	public List<OrderVO> orderInfo_list(Criteria cri) throws Exception;
	
	// 주문상품개수(페이징기능에 사용)
	public int getTotalCountOrder(Criteria cri) throws Exception;
	
	// 주문제품 상세
	public List<OrderDetailListVO> order_detail_list(long odr_code_pk) throws Exception;
	
	
	// 매출통계
	public List<OrderSaleDTO> order_sale(@Param("startDate") String startDate, @Param("endDate") String endDate) throws Exception;
	
}
