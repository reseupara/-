package com.reshome.domain;

import lombok.Data;

@Data
public class OrderDetailListVO {
	// od.odr_code_pk, od.gds_num_pk, od.odr_amount, od.odr_price, p.gds_name, p.gds_img
	
	private long odr_code_pk;	//주문번호
	private long gds_num_pk;	//상품번호
	private int odr_amount;		//주문 수량
	private int odr_price;		//상품금액
	
	// 기존 VO에 아래 두개를 추가하는 방법도 있다.
	private String gds_name;
	private String gds_img;

}
