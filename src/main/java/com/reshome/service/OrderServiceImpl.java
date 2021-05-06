package com.reshome.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reshome.domain.OrderDetailListVO;
import com.reshome.domain.OrderDetailVO;
import com.reshome.domain.OrderVO;
import com.reshome.dto.Criteria;
import com.reshome.dto.OrderSaleDTO;
import com.reshome.mapper.CartMapper;
import com.reshome.mapper.OrderMapper;

import lombok.Setter;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Setter(onMethod_ = @Autowired)
	private OrderMapper orderMapper;
	
	@Setter(onMethod_ = @Autowired)
	private CartMapper cartMapper;
	
	
	@Transactional
	@Override
	public void order_buy(OrderVO vo, String mbme_id) throws Exception {
		// TODO Auto-generated method stub
		orderMapper.order_add(vo);
		orderMapper.orderDetail_add(vo.getOdr_code_pk(), mbme_id);
		cartMapper.cartAll_delete(mbme_id);
		
	}

	@Transactional
	@Override
	public void orderDirect_buy(OrderVO vo, OrderDetailVO vo2) throws Exception {
		// TODO Auto-generated method stub
		orderMapper.order_add(vo);	// 파라미터가 참조형(주소값)
		vo2.setOdr_code_pk(vo.getOdr_code_pk());
		orderMapper.orderDirect_add(vo2);
		
	}

	@Override
	public List<OrderVO> orderInfo_list(Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return orderMapper.orderInfo_list(cri);
	}

	@Override
	public int getTotalCountOrder(Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return orderMapper.getTotalCountOrder(cri);
	}

	@Override
	public List<OrderDetailListVO> order_detail_list(long odr_code_pk) throws Exception {
		// TODO Auto-generated method stub
		return orderMapper.order_detail_list(odr_code_pk);
	}

	@Override
	public List<OrderSaleDTO> order_sale(String startDate, String endDate) throws Exception {
		// TODO Auto-generated method stub
		return orderMapper.order_sale(startDate, endDate);
	}


}
