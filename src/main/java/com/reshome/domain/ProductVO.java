package com.reshome.domain;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ProductVO {
	
	private int gds_num_pk;			//상품번호
	private String cg_code;			//현재 카테고리
	private String cg_code_up;		//상위 카테고리
	private String gds_name;		//상품이름
	private int gds_price;			//상품가격
	private int gds_discount;		//할인율
	private String gds_company;		//제조사
	private String gds_detail;		//상품 상세
	private String gds_img;			//상품 이미지
	private int gds_amount;			//제품 수량
	private String gds_buy;			//구매 여부
	private Date gds_date_reg;		//등록일자
	private Date gds_date_rt;		//정보 수정일
	
	// 파일첨부 파라미터작업
	private MultipartFile file1;
	

}
