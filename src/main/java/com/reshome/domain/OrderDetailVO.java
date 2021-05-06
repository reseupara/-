package com.reshome.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class OrderDetailVO {
	
	private long odr_code_pk;	//주문번호
	private long gds_num_pk;	//상품번호
	private int odr_amount;		//주문 수량
	private int odr_price;		//상품금액

}
