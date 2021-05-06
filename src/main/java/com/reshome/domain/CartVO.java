package com.reshome.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class CartVO {
	
	private long cart_code_pk;	// 장바구니 코드
	private long gds_num_pk;	// 상품번호
	private String mbme_id;		// 구매자 아이디
	private int cart_amount;	//구매 수량

}
