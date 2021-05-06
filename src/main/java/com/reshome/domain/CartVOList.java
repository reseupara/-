package com.reshome.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CartVOList {
	
	private long cart_code_pk;
	private String gds_img;
	private String gds_name;
	private int cart_amount;
	private long gds_price;
	
	

}
