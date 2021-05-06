package com.reshome.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class OrderVO {
	
	private long odr_code_pk;	//시퀀스				//주문번호	
	private String mbme_id;		// 세션정보 참조		//구매자 아이디			
	private String odr_name;						//이름
	private String odr_zipcode;						//우편번호
	private String odr_add;							//주소
	private String odr_add2;						//상세주소
	private String odr_phone;						//휴대폰번호
	private int odr_total_price;					// 총가격
	private Date odr_reg;	//DB(default-sysdate)	//등록일자
	
}
