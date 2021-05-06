package com.reshome.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ReviewVO {
	
	// rv_num_pk, mbme_id, gds_num, rv_content, rv_score, rv_date_reg
	
	private long rv_num_pk;		// 리뷰 글 번호
	private String mbme_id;		// 작성자 아이디
	private long gds_num_pk;		// 상품번호
	private String rv_content;	// 리뷰 내용
	private int rv_score;		// 별점
	private Date rv_date_reg;	// 등록일자
	
}
